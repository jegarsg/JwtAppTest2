package com.jegar.springjwt.payload.response;

import java.util.List;

public class JobResponseById {
    public int StatusCode;
    public String Message;

    public Position Data;

    public JobResponseById(int statusCode, String message, Position data) {
        this.StatusCode = statusCode;
        this.Message = message;
        this.Data = data;
    }
}