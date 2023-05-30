package com.example.demo.controller;


import com.example.demo.DTO.BodyResponse;
import com.example.demo.DTO.CredenzialiRequest;
import com.example.demo.Service.CredenzialiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/verificaCredenziale")
@RequiredArgsConstructor
public class CredenzialiController {

private final CredenzialiService service;

    @PostMapping("/myendpoint")
    public BodyResponse verificaCredenziale(@RequestBody CredenzialiRequest credenzialiRequest) {
        return service.verificaCredenziale(credenzialiRequest);
    }
}
