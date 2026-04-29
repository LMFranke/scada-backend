package com.franke.lucas.scada_backend.service;

import com.franke.lucas.scada_backend.model.Plc;
import com.franke.lucas.scada_backend.repository.PlcRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlcService {

    private final PlcRepository plcRepository;

    @Transactional(readOnly = true)
    public List<Plc> findAll() {
        return plcRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Plc findById(Long id) {
        return plcRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PLC with ID address not found: " + id));
    }

    @Transactional
    public Plc save(Plc plc) {
        if (plc.getId() == null && plcRepository.existsByIpAddress(plc.getIpAddress())) {
            log.warn("Try to register with duplicate IP: {}", plc.getIpAddress());
            throw new IllegalArgumentException("Already exists a PLC with this IP address.");
        }

        log.info("Saving PLC: {}", plc.getName());
        return plcRepository.save(plc);
    }

    @Transactional
    public void delete(Long id) {
        log.info("Deleting PLC with ID: {}", id);
        plcRepository.deleteById(id);
    }
}