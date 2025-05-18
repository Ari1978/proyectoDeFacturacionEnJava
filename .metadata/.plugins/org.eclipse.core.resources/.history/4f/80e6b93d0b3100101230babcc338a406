package com.coderhouse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderhouse.models.Turno;
import java.time.LocalDate;

public interface TurnoRepository extends JpaRepository<Turno, Long> {

    int countByFecha(LocalDate fecha);
}
