package com.example.demo.Entiy;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class User {
    @Id
    @NotEmpty
    @Min(value = 16, message = "La lunghezza minima del codice fiscale è 16 caratteri.")
    @Max(value = 16, message = "La lunghezza massima del codice fiscale è 16 caratteri.")
    @Column(name = "codice_fiscale")
    private String cf;
    @Column(name ="nome" )
    @NotNull
    private String nome;
    @Column(name ="cognome" )
    @NotNull
    private String cognome;
    @Column(name = "data-nascita-titolare")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull
    private LocalDate dataNascita;

}
