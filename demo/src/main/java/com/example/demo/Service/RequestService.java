package com.example.demo.Service;


import com.example.demo.DTO.RequestDto;
import com.example.demo.Entiy.Request;
import com.example.demo.Repository.RequestRepository;
import com.example.demo.converter.RequestConverter;
import com.example.demo.exeptionManagment.ApiRequestExeption;
import com.example.demo.exeptionManagment.ExceptionErrorEnum;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

@RequiredArgsConstructor
public class RequestService {

    @Autowired
    private  RequestRepository requestRepository;
    private final RequestConverter requestConverter;

    public List<RequestDto>  getallRequest(String cfTitolare) {
        List<Request> requestList = requestRepository.findAll();
        if (requestList.isEmpty()) {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
        }else {
            List<Request> list = requestList.stream().filter(n->n.getDatiTitolare().getCf().equals(cfTitolare)).collect(Collectors.toList());
        return requestConverter.toDTOList(list);
        }
    }
}
