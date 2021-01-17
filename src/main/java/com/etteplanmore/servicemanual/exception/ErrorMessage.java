package com.etteplanmore.servicemanual.exception;

import lombok.Data;

@Data
public class ErrorMessage {

	private String message;
	
	public ErrorMessage(String message) {
		this.message = message;
	}
}
