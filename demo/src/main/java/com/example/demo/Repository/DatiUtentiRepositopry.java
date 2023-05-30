package com.example.demo.Repository;

import com.example.demo.Entiy.DatiTitolare;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DatiUtentiRepositopry  extends JpaRepository<DatiTitolare,String> {

    Optional<DatiTitolare> findByCf(String cf);
}
