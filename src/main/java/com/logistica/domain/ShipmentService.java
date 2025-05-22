package com.logistica.domain;

import com.logistica.controller.contract.ShipmentCreationRequest;
import com.logistica.controller.contract.ShipmentResponse;
import com.logistica.data.model.Product;
import com.logistica.data.model.Shipment;
import com.logistica.data.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ShipmentService {
    @Autowired
    private ShipmentRepository shipmentRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private TruckService truckService;
    @Autowired
    private WarehouseService warehouseService;

    public List<ShipmentResponse> findAll() {
        var iterable = shipmentRepository.findAll();
        return StreamSupport
                .stream(iterable.spliterator(), false)
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public Optional<Shipment> findById(Long id) {
        return shipmentRepository.findById(id);
    }

    public ShipmentResponse create(ShipmentCreationRequest request) {
        var truck = truckService.findById(request.truckId()).get();
        var warehouse = warehouseService.findById(request.warehouseId()).get();
        var products = request.productIds()
                .stream()
                .map(productService::findById)
                .filter(Optional::isPresent)     // Keep only the Optionals that are present
                .map(Optional::get)              // Extract the value from Optional
                .toList();
        var shipment = new Shipment(truck, warehouse, products);
        return convert(shipmentRepository.save(shipment));
    }

    public void delete(Long id) {
        shipmentRepository.deleteById(id);
    }

    private ShipmentResponse convert(Shipment s) {
        return new ShipmentResponse(
                s.getTruck().getId(),
                s.getWarehouse().getId(),
                s.getProducts().stream().map(Product::getId).toList()
        );
    }

}
