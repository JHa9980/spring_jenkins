package com.skala.springbootsample.dto;

import lombok.Data;

@Data
public class HelloResponse {
    private int statusCode;
    private String message;
}
