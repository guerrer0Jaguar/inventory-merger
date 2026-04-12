package org.guerrer0jaguar.inventory.merger.controller;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CustomHTTPmessage {
	private final int status;
	private final String message;
	private final LocalDateTime timestamp;
	
	public CustomHTTPmessage(int status, String message, LocalDateTime timestamp) {
		super();
		this.status = status;
		this.message = message;
		this.timestamp = timestamp;
	}		
}
