package com.franke.lucas.scada_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "temperature_readings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemperatureReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fermenter_id", nullable = false)
    private Fermenter fermenter;

    @Column(name = "recorded_at", nullable = false)
    private LocalDateTime recordedAt;

    @Column(nullable = false)
    private Double temperature;

    @Column(name = "percentage_difference")
    private Double percentageDifference;

}
