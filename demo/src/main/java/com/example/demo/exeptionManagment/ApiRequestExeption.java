package com.example.demo.exeptionManagment;

public class ApiRequestExeption extends  RuntimeException {
    public ApiRequestExeption(String message) {
        super(message);
    }

    public ApiRequestExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
