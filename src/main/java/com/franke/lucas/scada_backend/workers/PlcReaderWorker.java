package com.franke.lucas.scada_backend.workers;

import com.franke.lucas.scada_backend.integration.SlmpProtocolClient;
import com.franke.lucas.scada_backend.model.Plc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PlcReaderWorker {

    private final SlmpProtocolClient slmpClient;
    // private final TemperatureService temperatureService; -> injetar depois para salvar os dados

    @Async("plcTaskExecutor")
    public void readPlcData(Plc plc) {
        String threadName = Thread.currentThread().getName();
        log.info("[{}] Iniciando processamento do PLC: {} ({}:{})",
                threadName, plc.getName(), plc.getIpAddress(), plc.getPort());

        try {
            boolean isConnected = slmpClient.connect(plc.getIpAddress(), plc.getPort());

            if (!isConnected) {
                log.error("[{}] Falha ao conectar no PLC: {}", threadName, plc.getName());
                return;
            }

            // Exemplo estrutural de iteração sobre os fermentadores do PLC
            /*
            for (Fermenter fermenter : plc.getFermenters()) {
                if (!fermenter.getActive()) continue;

                String baseAddress = "D" + (fermenter.getIndexNumber() * 100);
                short[] dataBlock = slmpClient.readDeviceBlock(baseAddress, 10);

                // Tratar os bytes e salvar via service
            }
            */

        } catch (Exception e) {
            log.error("[{}] Erro crítico na comunicação com o PLC {}: {}",
                    threadName, plc.getName(), e.getMessage(), e);
        } finally {
            slmpClient.disconnect();
            log.debug("[{}] Processamento finalizado para o PLC: {}", threadName, plc.getName());
        }
    }
}