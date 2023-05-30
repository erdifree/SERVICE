package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AccompagnatoriResponse {

    private String nomeAccompagnatore;

    private LocalDate dataNascitaAccompagnatore;

    private String cognomeAccompagnatore ;

}
