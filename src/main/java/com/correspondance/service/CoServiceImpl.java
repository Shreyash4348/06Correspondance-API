package com.correspondance.service;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.correspondance.bindings.CoResponse;
import com.correspondance.entity.CitizenAppEntity;
import com.correspondance.entity.CorrspTriggerEntity;
import com.correspondance.entity.DcCaseEntity;
import com.correspondance.entity.EligibilityDEntity;
import com.correspondance.repository.CitizenAppRepo;
import com.correspondance.repository.CoTriggerRepo;
import com.correspondance.repository.DcCaseRepo;
import com.correspondance.repository.EligiDetailsRepo;
import com.correspondance.utils.EmailUtils;
import com.lowagie.text.Document;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class CoServiceImpl implements ICoService {

	

	@Autowired
	private EligiDetailsRepo eligRepo;
	
	@Autowired
	private CoTriggerRepo coTriRepo;

	@Autowired
	private CitizenAppRepo appRepo;

	@Autowired
	private DcCaseRepo dcRepo;
	
	@Autowired
	private EmailUtils email;

	@Override
	public CoResponse processPendingTriggers() {

		CoResponse response = new CoResponse();
		
		
		CitizenAppEntity appEntity = null;
		// fetch all pending triggers
		List<CorrspTriggerEntity> pendingTrgs = coTriRepo.findByTrgStatus("Pending");

		response.setTotalTriggers(Long.valueOf(pendingTrgs.size()));
		
		// process each pending trigger
		for (CorrspTriggerEntity entity : pendingTrgs) {
			// Get eligibility data based on case Number
			EligibilityDEntity eligRecord = eligRepo.findByCaseNumber(entity.getCaseNum());

			// get citizen data based on case number
			Optional<DcCaseEntity> byId = dcRepo.findById(entity.getCaseNum());
			if (byId.isPresent()) {
				DcCaseEntity dcCaseEntity = byId.get();
				Integer appId = dcCaseEntity.getAppId();
				Optional<CitizenAppEntity> appEntityOptional = appRepo.findById(appId);
				if (appEntityOptional.isPresent()) {
					appEntity = appEntityOptional.get();
				}

			}
			
			Long failedCount = 0l;
			Long successCount = 0l;
			// generate p d f with eligibility details
			// send p d f to citizen mail
			try {
				generateAndSendPdf(eligRecord, appEntity);
				successCount++;
			} catch (Exception e) {
				
				e.printStackTrace();
				failedCount++;
			}
			
			response.setSuccTriggers(successCount);
			response.setFailedTriggers(failedCount);
			
			// store the p d f & update trigger as complete
		}

		// return summary
		return response;
	}

	private void generateAndSendPdf(EligibilityDEntity eligData, CitizenAppEntity appEntity) throws Exception {
		
		Document document = new Document(PageSize.A4);

		FileOutputStream fos = null;
		File file = new File(eligData.getCaseNumber()+".pdf");
		try {
			fos = new FileOutputStream(file);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		PdfWriter.getInstance(document, fos);
		
		document.open();
		com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);

		Paragraph p = new Paragraph("Eligibility Report", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		PdfPTable table = new PdfPTable(7);

		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 1.5f, 3.0f, 1.5f, 3.0f }); // seven columns
		table.setSpacingBefore(10);

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);

		font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Citizen Name", font));
		table.addCell(cell);

//		cell.setPhrase(new Phrase("Case Number", font));
//		table.addCell(cell);

		cell.setPhrase(new Phrase("Plan Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Plan Status", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Plan Start Date", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Plan End Date", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Benifit Amount", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Deniel Reason", font));
		table.addCell(cell);

		table.addCell(appEntity.getFullName());
		table.addCell(eligData.getPlanName());
		table.addCell(eligData.getPlanStatus());
		table.addCell(eligData.getPlanStartDate() + "");
		table.addCell(eligData.getPlanEndDate() + "");
		table.addCell(eligData.getBenifitAmt() + "");
		table.addCell(eligData.getDenialReason() + "");

		document.add(table);
		document.close();
		String subject = "HIS Eligibility Info";
		String body = "Please collect you Report of Eligiblity info ";
		
		email.sendEmailMessage(appEntity.getEmail(), subject, body, file);
		
		updateTriggerTable(eligData.getCaseNumber(), file);
	}
	
	private void updateTriggerTable(Long caseNum, File file) throws Exception {
	    CorrspTriggerEntity coEntity = coTriRepo.findByCaseNum(caseNum);

	    long fileSize = file.length();
	    if (fileSize > Integer.MAX_VALUE) {
	        throw new IllegalArgumentException("File is too large to process");
	    }

	    byte[] arr = new byte[(int) fileSize];

	    try (FileInputStream fis = new FileInputStream(file)) {
	        fis.read(arr);
	    }

	    coEntity.setCoPdf(arr);
	    coEntity.setTrgStatus("Completed");
	    coTriRepo.save(coEntity);
	}

}
