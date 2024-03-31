package com.example.myapp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDetails {
    public ErrorDetails(Date date, String message2, String description) {
		// TODO Auto-generated constructor stub
	}
	private Date timestamp;
    private String message;
    private String details;
}