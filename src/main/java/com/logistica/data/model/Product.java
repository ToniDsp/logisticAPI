package com.logistica.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PRODUCTS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private Long id;

    @Column(nullable = false)
    @NotBlank
    @JsonProperty("name")
    private String name;

    @Column(nullable = false)
    @NotBlank
    @JsonProperty("provider_id")
    private Long provider_id;

    @Column
    @NotBlank
    @JsonProperty("stock")
    private double stock;

    @Column
    @NotBlank
    @JsonProperty("measure")
    private String measure;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @NotBlank
    @JsonProperty("categoryId")
    private Categories category;


    public Product(String name, Long provider_id, double stock, String measure, Categories category) {
        this.name = name;
        this.provider_id = provider_id;
        this.stock = stock;
        this.measure = measure;
        this.category = category;
    }

}


