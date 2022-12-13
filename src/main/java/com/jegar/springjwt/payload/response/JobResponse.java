package com.jegar.springjwt.payload.response;

import java.util.List;

public class JobResponse {
    public int StatusCode;
    public String Message;

    public List<Position> Data;

    public JobResponse(int statusCode, String message, List<Position> data) {
        this.StatusCode = statusCode;
        this.Message = message;
        this.Data = data;
    }
}