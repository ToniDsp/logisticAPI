package com.logistica.controller;

import com.logistica.data.model.Truck;
import com.logistica.domain.TruckService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/trucks")
@RestController
public class TruckController {

    @Autowired
    private TruckService truckService;

    @GetMapping("")
    @Operation(summary = "Get all trucks")
    public ResponseEntity<Iterable<Truck>> getTrucks() {
        return ResponseEntity.ok(truckService.findAll());
    }

    @PostMapping("")
    @Operation(summary = "Create a new truck")
    public ResponseEntity<Truck> createTruck(@Valid @RequestBody Truck truck) {
        return ResponseEntity.ok(truckService.save(truck));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete a truck")
    public ResponseEntity<Void> deleteTruck(@PathVariable("id") Long id) {
        truckService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
