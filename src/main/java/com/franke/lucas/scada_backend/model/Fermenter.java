package com.franke.lucas.scada_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fermenters")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fermenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plc_id", nullable = false)
    private Plc plc;

    @Column(nullable = false)
    private String name;

    @Column(name = "index_number", nullable = false)
    private Integer indexNumber;

}