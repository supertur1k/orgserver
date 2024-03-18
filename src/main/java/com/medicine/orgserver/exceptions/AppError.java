package com.medicine.orgserver.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class AppError {
    public AppError(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }

    private int status;
    private String message;
    private Date timestamp;
}
