package com.example.demo.Repository;

import com.example.demo.Entiy.DatiTitolare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;



@Repository
@Transactional
public interface DatiUtentiRepositopry  extends JpaRepository<DatiTitolare,String> {
    Optional<DatiTitolare> findByCf(String cf);
}
