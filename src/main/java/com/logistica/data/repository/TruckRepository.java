package com.logistica.data.repository;

import com.logistica.data.model.Truck;
import org.springframework.data.repository.CrudRepository;


public interface TruckRepository extends CrudRepository<Truck, Long> {
}
