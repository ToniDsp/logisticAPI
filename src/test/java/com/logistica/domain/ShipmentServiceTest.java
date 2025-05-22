package com.logistica.domain;

import com.logistica.controller.contract.ShipmentCreationRequest;
import com.logistica.controller.contract.ShipmentResponse;
import com.logistica.data.model.Shipment;
import com.logistica.data.repository.ShipmentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static com.logistica.fixtures.ProductFixture.TEST_PRODUCT_1;
import static com.logistica.fixtures.ShipmentFixture.TEST_SHIPMENTS;
import static com.logistica.fixtures.ShipmentFixture.TEST_SHIPMENT_1;
import static com.logistica.fixtures.TruckFixture.TEST_TRUCK;
import static com.logistica.fixtures.WarehouseFixture.TEST_WAREHOUSE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ShipmentServiceTest {

    @Mock
    ShipmentRepository shipmentRepository;
    @Mock
    ProductService productService;
    @Mock
    TruckService truckService;
    @Mock
    WarehouseService warehouseService;

    @InjectMocks
    private ShipmentService shipmentService;

    public ShipmentServiceTest() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void findAllShipments() {
        when(shipmentRepository.findAll()).thenReturn(TEST_SHIPMENTS);

        var result = shipmentService.findAll();

        var expected = List.of(
                new ShipmentResponse(1L, 1L, List.of(1L)),
                new ShipmentResponse(1L, 1L, List.of(2L))
        );
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void notFindAnyShipment() {
        when(shipmentRepository.findAll()).thenReturn(List.of());

        var result = shipmentService.findAll();

        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    public void createShipment() {
        when(truckService.findById(anyLong())).thenReturn(Optional.of(TEST_TRUCK));
        when(warehouseService.findById(anyLong())).thenReturn(Optional.of(TEST_WAREHOUSE));
        when(productService.findById(1L)).thenReturn(Optional.of(TEST_PRODUCT_1));
        when(shipmentRepository.save(any())).thenReturn(TEST_SHIPMENT_1);
        ArgumentCaptor<Shipment> shipmentCaptor = ArgumentCaptor.forClass(Shipment.class);
        var expected = new ShipmentResponse(1L, 1L, List.of(1L));

        var result = shipmentService.create(new ShipmentCreationRequest(1L, 1L, List.of(1L)));

        assertThat(result).isEqualTo(expected);
        verify(shipmentRepository).save(shipmentCaptor.capture());
        var savedShipment = shipmentCaptor.getValue();
        assertThat(savedShipment.getId()).isNull();
        assertThat(savedShipment.getTruck()).isEqualTo(TEST_TRUCK);
        assertThat(savedShipment.getWarehouse()).isEqualTo(TEST_WAREHOUSE);
        assertThat(savedShipment.getProducts()).isEqualTo(List.of(TEST_PRODUCT_1));
    }
}
