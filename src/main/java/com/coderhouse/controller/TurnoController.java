package com.coderhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.coderhouse.models.Turno;
import com.coderhouse.service.TurnoService;

@RestController
@RequestMapping("/a/turnos")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    @GetMapping("/{turnoId}")
    public ResponseEntity<?> getTurnoById(@PathVariable Long turnoId) {
        try {
            Turno turno = turnoService.findById(turnoId);
            return ResponseEntity.ok(turno); // 200
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build(); //404
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Turno>> getAllTurnos() {
        try {
            List<Turno> turnos = turnoService.findAll();
            return ResponseEntity.ok(turnos); // 200
        } catch (Exception err) {
            return ResponseEntity.internalServerError().build(); //500
        }
    }

    @PostMapping("/asignar-turno")
    public ResponseEntity<?> asignarTurno(@RequestBody Turno turno) {
        try {
            Turno turnoAsignado = turnoService.save(turno);
            return ResponseEntity.status(HttpStatus.CREATED).body(turnoAsignado); //201
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
        }
    }
}
