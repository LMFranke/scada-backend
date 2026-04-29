package com.franke.lucas.scada_backend.controller;

import com.franke.lucas.scada_backend.model.Plc;
import com.franke.lucas.scada_backend.repository.PlcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plcs")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PlcController {

    private final PlcRepository plcRepository;

    // Retorna a lista de todos os PLCs cadastrados
    @GetMapping
    public ResponseEntity<List<Plc>> getAllPlcs() {
        return ResponseEntity.ok(plcRepository.findAll());
    }

    // Salva um novo PLC no banco de dados
    @PostMapping
    public ResponseEntity<Plc> createPlc(@RequestBody Plc newPlc) {
        Plc savedPlc = plcRepository.save(newPlc);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPlc);
    }
}
