package com.example.demo.converter;


import com.example.demo.DTO.RequestDto;
import com.example.demo.Entiy.Request;
import jakarta.persistence.Convert;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;


@Service
public class RequestConverter extends AbstractConverter<Request, RequestDto> {

    @Override
    public Request toEntity(RequestDto requestDto) {
        Request request=null;
        if (requestDto != null) {
           request=Request
                   .builder()
                   .tipo(requestDto.getTipo())
                   .stazioneOrigine(requestDto.getStazioneOrigine())
                   .stazioneDestinazione(requestDto.getStazioneDestinazione())
                   .dataInizioVal(requestDto.getDataInizioVal())
                   .dataFineVal(requestDto.getDataFineVal())
                   .percSconto(requestDto.getPercSconto())
                   .datiTitolare(requestDto.getDatiTitolare())
                   .build();

        }
        return request;
    }

    @Override
    public RequestDto toDTO(Request request) {
        RequestDto requestDto=null;
        if (request != null) {
            requestDto=RequestDto
                    .builder()
                    .tipo(request.getTipo())
                    .stazioneOrigine(request.getStazioneOrigine())
                    .stazioneDestinazione(request.getStazioneDestinazione())
                    .dataInizioVal(request.getDataInizioVal())
                    .dataFineVal(request.getDataFineVal())
                    .percSconto(request.getPercSconto())
                    .datiTitolare(request.getDatiTitolare())
                    .build();

        }
        return requestDto;
    }
}
