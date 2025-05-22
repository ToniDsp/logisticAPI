package com.logistica.data.repository;

import com.logistica.data.model.Shipment;
import org.springframework.data.repository.CrudRepository;


public interface ShipmentRepository extends CrudRepository<Shipment, Long> {
}
