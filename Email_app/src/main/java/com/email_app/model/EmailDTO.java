package com.email_app.model;

import lombok.Data;

@Data
public class EmailDTO {

	private String subject;
	private String description;
	private Boolean star;
}
