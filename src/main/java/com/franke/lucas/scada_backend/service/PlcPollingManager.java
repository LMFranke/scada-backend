package com.franke.lucas.scada_backend.service;

import com.franke.lucas.scada_backend.model.Plc;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlcPollingManager {

    private final PlcReaderWorker worker;

    @Scheduled(fixedRate = 5000)
    public void schedulePLCReading() {
        System.out.println("--- Scheduler wake up: Triggering the reads ---");
        List<Plc> activePlcs = List.of(new Plc(), new Plc());

        for (Plc plc : activePlcs) {
            worker.readPlcData(plc);
        }
    }
}
