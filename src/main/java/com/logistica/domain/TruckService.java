package com.logistica.domain;

import com.logistica.data.model.Truck;
import com.logistica.data.repository.TruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TruckService {
    @Autowired
    TruckRepository truckRepository;

    public List<Truck> findAll() {
        var iterable = truckRepository.findAll();
        return StreamSupport
                .stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    public Optional<Truck> findById(Long id) {
        return truckRepository.findById(id);
    }

    public Truck save(Truck truck) {
        return truckRepository.save(truck);
    }

    public void delete(Long id) {
        truckRepository.deleteById(id);
    }
}
