package com.franke.lucas.scada_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recipe_steps")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    @Column(name = "step_order", nullable = false)
    private Integer stepOrder;

    @Column(name = "target_temperature", nullable = false)
    private Double targetTemperature;

    @Column(name = "ramp_rate", nullable = false)
    private Double rampRate;

    @Column(name = "hold_time_minutes", nullable = false)
    private Integer holdTimeMinutes;

}