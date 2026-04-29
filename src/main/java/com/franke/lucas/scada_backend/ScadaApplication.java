package com.franke.lucas.scada_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // Ativa o relógio interno para disparar o PlcPollingManager a cada 5 segundos
@EnableAsync      // Permite que o PlcReaderWorker jogue o trabalho para a Thread Pool paralela
public class ScadaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScadaApplication.class, args);

		System.out.println("=========================================================");
		System.out.println(" SCADA Backend started with success.");
		System.out.println("=========================================================");
	}

}