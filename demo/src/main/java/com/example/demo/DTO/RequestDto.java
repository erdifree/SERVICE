package com.example.demo.DTO;


import com.example.demo.Entiy.DatiTitolare;
import com.example.demo.Entiy.PercSconto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RequestDto {

    private  String tipo;
    private  Integer stazioneOrigine;
    private  Integer stazioneDestinazione;
    private LocalDate dataInizioVal;
    private LocalDate dataFineVal;
    private PercSconto percSconto;
    private DatiTitolare datiTitolare;
}
