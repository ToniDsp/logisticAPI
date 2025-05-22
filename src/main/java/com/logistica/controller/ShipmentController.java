package com.logistica.controller;

import com.logistica.controller.contract.ShipmentCreationRequest;
import com.logistica.controller.contract.ShipmentResponse;
import com.logistica.domain.ShipmentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/shipments")
@RestController
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @GetMapping("")
    @Operation(summary = "Get all Shipments")
    public ResponseEntity<Iterable<ShipmentResponse>> getShipments() {
        return ResponseEntity.ok(shipmentService.findAll());
    }

    @PostMapping("")
    @Operation(summary = "Create a new Shipment")
    public ResponseEntity<ShipmentResponse> createShipment(
            @Valid @RequestBody ShipmentCreationRequest request
    ) {
        return ResponseEntity.ok(shipmentService.create(request));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete a shipment")
    public ResponseEntity<Void> deleteShipment(@PathVariable("id") Long id) {
        shipmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
