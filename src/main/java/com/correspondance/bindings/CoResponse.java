package com.correspondance.bindings;

import lombok.Data;

@Data
public class CoResponse {

	private Long totalTriggers;
	private Long succTriggers;
	private Long failedTriggers;
}
