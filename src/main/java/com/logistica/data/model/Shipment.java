package com.logistica.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "SHIPMENTS")
@Getter
@Setter
@AllArgsConstructor
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "truck_id", nullable = false)
    @JsonProperty("truck")
    private Truck truck;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", nullable = false)
    @JsonProperty("warehouse")
    private Warehouse warehouse;

    @ManyToMany
    @JoinTable(
            name = "shipment_products",
            joinColumns = @JoinColumn(name = "shipment_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @JsonProperty("products")
    private List<Product> products;

    public Shipment(Truck truck, Warehouse warehouse, List<Product> products) {
        this.truck = truck;
        this.warehouse = warehouse;
        this.products = products;

    }

    public Shipment() {
    }
}


