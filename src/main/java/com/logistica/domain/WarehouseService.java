package com.logistica.domain;

import com.logistica.data.model.Warehouse;
import com.logistica.data.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;

    public List<Warehouse> findAll() {
        var iterable = warehouseRepository.findAll();
        return StreamSupport
                .stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    public Optional<Warehouse> findById(Long id) {
        return warehouseRepository.findById(id);
    }

    public Warehouse save(Warehouse truck) {
        return warehouseRepository.save(truck);
    }

    public void delete(Long id) {
        warehouseRepository.deleteById(id);
    }
}
