package com.example.demo.DTO;

import com.example.demo.Entiy.ElencoCodici;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BodyResponse {

    private String nomeTitolare;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate dataNascitaTitolare;
    private String esito;
    private String cognomeTitolare;
    private String nomeAccompagnatore;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate dataNascitaAccompagnatore;
    private String cognomeAccompagnatore ;
    private ElencoCodici elencoCodici;


    public BodyResponse(String nomeTitolare, LocalDate dataNascitaTitolare, String esito, String cognomeTitolare , ElencoCodici elencoCodici) {
        this.nomeTitolare = nomeTitolare;

        this.dataNascitaTitolare = dataNascitaTitolare;
        this.esito = esito;
        this.cognomeTitolare = cognomeTitolare;
        this.elencoCodici = elencoCodici;
    }





}
