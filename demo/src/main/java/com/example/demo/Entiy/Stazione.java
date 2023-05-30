package com.example.demo.Entiy;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Stazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = ("stazione"))
    private String nomeStazione;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "##.######")
    private double latitude;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "##.######")
    private double longitude;
}
