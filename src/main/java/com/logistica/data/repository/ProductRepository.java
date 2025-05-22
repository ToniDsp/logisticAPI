package com.logistica.data.repository;

import com.logistica.data.model.Product;
import org.springframework.data.repository.CrudRepository;


public interface ProductRepository extends CrudRepository<Product, Long> {
}
