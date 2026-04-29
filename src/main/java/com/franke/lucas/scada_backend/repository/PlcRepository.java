package com.franke.lucas.scada_backend.repository;

import com.franke.lucas.scada_backend.model.Plc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlcRepository extends JpaRepository<Plc, Long> {

    // O Spring Data JPA gera os métodos findAll(), save(), findById() e deleteById() automaticamente.

    // Abaixo estão exemplos de "Derived Queries" (Consultas Derivadas).
    // O Spring lê o nome do metodo em inglês e cria a query SQL sozinho.
    // São muito úteis para fazer validações na hora de guardar um novo registo.

    // Procura um PLC específico pelo seu endereço IP
    Optional<Plc> findByIpAddress(String ipAddress);

    // Verifica se já existe algum PLC registado com um determinado nome
    boolean existsByName(String name);

    // Verifica se já existe algum PLC registado com um determinado IP
    boolean existsByIpAddress(String ipAddress);
}
