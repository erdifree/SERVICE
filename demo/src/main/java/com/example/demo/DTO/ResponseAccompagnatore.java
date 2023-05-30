package com.example.demo.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseAccompagnatore {
    private String Nome;
    private String dataNascitaAccompagnatore;
    private String cognomeAccompagnatore;
}
