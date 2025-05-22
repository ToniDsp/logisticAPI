package com.logistica.controller.contract;


import java.util.List;

public record ShipmentResponse(
        Long truckId,
        Long warehouseId,
        List<Long> productIds
) {
}
