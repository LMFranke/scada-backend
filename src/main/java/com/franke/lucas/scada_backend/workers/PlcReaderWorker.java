package com.franke.lucas.scada_backend.workers;

import com.franke.lucas.scada_backend.model.Plc;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class PlcReaderWorker {

    @Async("plcTaskExecutor")
    public void readPlcData(Plc plc) {
        try {
            System.out.println("Starting reading from PLC: " + plc.getName() + " on thread: " + Thread.currentThread().getName());

            // slmpClient.disconnect();

        } catch (Exception e) {
            System.err.println("Error while trying to communicate with PLC " + plc.getName() + ": " + e.getMessage());
        }
    }
}
