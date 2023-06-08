package com.example.demo.controller;


import com.example.demo.DTO.RequestDto;
import com.example.demo.Entiy.Request;
import com.example.demo.Repository.RequestRepository;
import com.example.demo.Service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/richeste")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;

    @GetMapping("/{cfTitolare}")
    public List<RequestDto> getallRequestOfEachLogedUeser(@PathVariable String cfTitolare){
     return requestService.getallRequest(cfTitolare);
    }

}
