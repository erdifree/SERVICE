package com.example.demo.Entiy;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


//    @Enumerated(EnumType.STRING)
//    private TipoDiBiglietto tipoDiBiglietto;

    @Column(name = "tipo")
    private  String tipo;

    @Column(name = "origine")
    private  Integer stazioneOrigine;

    @Column(name = "destinazione")
    private  Integer stazioneDestinazione;

    @Column(name = "data-inizio-val")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInizioVal;

    @Column(name = "data-fine-val")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFineVal;
    @Enumerated(EnumType.STRING)
    private PercSconto percSconto;
    @ManyToOne()
    @JsonBackReference
    private DatiTitolare datiTitolare;
}
