package com.logistica.controller.contract;


import java.util.List;

public record ShipmentCreationRequest(
        Long truckId,
        Long warehouseId,
        List<Long> productIds
) {
}
