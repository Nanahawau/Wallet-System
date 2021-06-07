package com.fgt.walletsystem.enums;

public enum ResponseCode {
	SUCCESS("000", "Successful"), ERROR("99", "Failed"),
	WALLET_EXISTS("99", "A wallet already exists for this email"),
	WALLET_CREATION_FAILED("100", "An error occurred while creating wallet"),
	WALLET_NOT_EXIST("101", "Wallet doesn't exist");
	
	private String code;
	private String message;

	ResponseCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
