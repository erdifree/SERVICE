package com.example.demo.exeptionManagment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;


@RequiredArgsConstructor
public class ApiExeption {

    private  final String message;
    private  final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;


}
