package com.franke.lucas.scada_backend.controller;

import com.franke.lucas.scada_backend.model.Plc;
import com.franke.lucas.scada_backend.service.PlcService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plcs")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Essencial: Permite que o Flutter Web aceda à API sem ser bloqueado pela política de CORS do navegador
public class PlcController {

    private final PlcService plcService;

    // Retorna a lista de todos os PLCs cadastrados no banco
    @GetMapping
    public ResponseEntity<List<Plc>> getAllPlcs() {
        List<Plc> plcs = plcService.findAll();
        return ResponseEntity.ok(plcs);
    }

    // Busca um PLC específico pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Plc> getPlcById(@PathVariable Long id) {
        Plc plc = plcService.findById(id);
        return ResponseEntity.ok(plc);
    }

    // Salva um novo PLC no banco de dados
    @PostMapping
    public ResponseEntity<Plc> createPlc(@RequestBody Plc newPlc) {
        Plc savedPlc = plcService.save(newPlc);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPlc);
    }

    // Deleta um PLC do banco de dados
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlc(@PathVariable Long id) {
        plcService.delete(id);
        return ResponseEntity.noContent().build();
    }
}