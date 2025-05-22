package com.logistica.controller;

import com.logistica.data.model.Warehouse;
import com.logistica.domain.WarehouseService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/warehouses")
@RestController
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @GetMapping("")
    @Operation(summary = "Get all warehouses")
    public ResponseEntity<Iterable<Warehouse>> getWarehouse() {
        return ResponseEntity.ok(warehouseService.findAll());
    }

    @PostMapping("")
    @Operation(summary = "Create a new Warehouse")
    public ResponseEntity<Warehouse> createWarehouse(@Valid @RequestBody Warehouse warehouse) {
        return ResponseEntity.ok(warehouseService.save(warehouse));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete a Warehouse")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable("id") Long id) {
        warehouseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
