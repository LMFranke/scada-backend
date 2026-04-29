package com.franke.lucas.scada_backend.service;

import com.franke.lucas.scada_backend.model.Plc;
import com.franke.lucas.scada_backend.workers.PlcReaderWorker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlcPollingManager {

    private final PlcService plcService;
    private final PlcReaderWorker plcWorker;

    // Roda a cada 5 segundos (5000ms). Você pode externalizar isso no application.properties depois
    @Scheduled(fixedRate = 5000)
    public void schedulePlcReading() {
        log.debug("--- [SCHEDULER] Acordou: Disparando ciclo de leituras ---");

        try {
            List<Plc> activePlcs = plcService.findAll();

            if (activePlcs.isEmpty()) {
                log.debug("Nenhum PLC cadastrado no sistema para monitorar.");
                return;
            }

            for (Plc plc : activePlcs) {
                // Dispara a leitura assíncrona. O for loop não fica travado aguardando o PLC responder.
                plcWorker.readPlcData(plc);
            }
        } catch (Exception e) {
            log.error("Erro ao disparar o agendamento de leituras: {}", e.getMessage(), e);
        }
    }
}