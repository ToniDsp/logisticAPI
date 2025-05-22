package com.logistica.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "PROVIDERS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private Long id;

    @Column(nullable = false)
    @NotBlank
    @Pattern(regexp = "^[A-Za-z ]+$")
    @JsonProperty("name")
    private String name;

    @Column
    @NotBlank
    @Email
    @JsonProperty("contact")
    private String contact;

    @ManyToMany
    @JoinTable(
            name = "providers_products",
            joinColumns = @JoinColumn(name = "provider_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @JsonProperty("Products")
    private List<Product> product;

    public Provider(String name, String contact, List<Product> product) {
        this.name = name;
        this.contact = contact;
        this.product = product;
    }

}