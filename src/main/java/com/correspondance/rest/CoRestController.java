package com.correspondance.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.correspondance.bindings.CoResponse;
import com.correspondance.service.ICoService;

@RestController
public class CoRestController {

	@Autowired
	private ICoService service;
	
	@GetMapping("/process")
	public CoResponse processTriggers()
	{
		return service.processPendingTriggers();
	}
}
