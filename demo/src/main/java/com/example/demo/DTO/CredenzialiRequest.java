package com.example.demo.DTO;

import com.example.demo.Entiy.Esito;
import com.example.demo.Entiy.PercSconto;
import com.example.demo.Entiy.Stazione;
import com.example.demo.Entiy.TipoDiBiglietto;
import com.example.demo.Service.CredenzialiService;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;


import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CredenzialiRequest {
    private Integer id;
    private String credenziale;
    private String cfTitolare;
    private String cfAccompagnatore;
    private String tipoDiBiglietto;
    private  String origine;
    private  String origineCoordinate;
    private  String destinazione;
    private  String destinazioneCoordinate;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate dataInizioVal;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate dataFineVal;
    private PercSconto percSconto;
    private Esito esito;



}
