package com.example.demo.Entiy;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Credenziali {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty
    @Min(value = 8, message = "La lunghezza minima delle credenziali è 8 caratteri.")
    @Max(value = 8, message = "La lunghezza massima delle credenziali è 8 caratteri.")
    private String credenziale;

    @OneToOne(mappedBy = "credenziali")
    private DatiTitolare utente;


}
