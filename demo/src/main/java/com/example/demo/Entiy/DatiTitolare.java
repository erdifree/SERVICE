package com.example.demo.Entiy;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.concurrent.ScheduledExecutorTask;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatiTitolare extends User {
    @OneToOne
    @JsonManagedReference
    private Credenziali credenziali;

    @OneToOne
    @JsonManagedReference
    private DatiAccompagnatore datiAccompagnatore;

    @JsonBackReference
    @OneToMany(mappedBy = "datiTitolare")
    private Set<Request> requestSet;
}
