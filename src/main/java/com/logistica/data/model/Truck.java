package com.logistica.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TRUCKS")
@Getter
@Setter
@AllArgsConstructor
public class Truck {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private Long id;

    @Column(nullable = false)
    @NotBlank
    @JsonProperty("plateNumber")
    private String plateNumber;

    public Truck(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Truck() {
    }
}


