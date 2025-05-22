package com.logistica.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "WAREHOUSES")
@Getter
@Setter
@AllArgsConstructor
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private Long id;

    @Column(nullable = false)
    @NotBlank
    @JsonProperty("name_warehouse")
    private String name;

    @Column(nullable = false)
    @NotBlank
    @JsonProperty("manager")
    private String manager;

    @OneToMany
    @JoinTable(
            name = "warehouse_products",  // The join table that links Warehouse and Product
            joinColumns = @JoinColumn(name = "warehouse_id"),  // foreign key for Warehouse
            inverseJoinColumns = @JoinColumn(name = "product_id")  // foreign key for Product
    )
    @JsonProperty("products")
    private List<Product> products;

    public Warehouse(String name, String manager, List<Product> products) {
        this.name = name;
        this.products = products;
        this.manager = manager;
    }
    public Warehouse(String name, String manager){
        this.name = name;
        this.manager = manager;
    }

    public Warehouse() {
    }
}


