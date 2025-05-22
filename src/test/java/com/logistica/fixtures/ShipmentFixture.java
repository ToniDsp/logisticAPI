package com.logistica.fixtures;

import com.logistica.data.model.Shipment;

import java.util.List;

import static com.logistica.fixtures.ProductFixture.TEST_PRODUCT_1;
import static com.logistica.fixtures.ProductFixture.TEST_PRODUCT_2;
import static com.logistica.fixtures.TruckFixture.TEST_TRUCK;
import static com.logistica.fixtures.WarehouseFixture.TEST_WAREHOUSE;

public class ShipmentFixture {

    public static Shipment TEST_SHIPMENT_1 =
            new Shipment(1L, TEST_TRUCK, TEST_WAREHOUSE, List.of(TEST_PRODUCT_1));
    public static Shipment TEST_SHIPMENT_2 =
            new Shipment(2L, TEST_TRUCK, TEST_WAREHOUSE, List.of(TEST_PRODUCT_2));

    public static List<Shipment> TEST_SHIPMENTS = List.of(TEST_SHIPMENT_1, TEST_SHIPMENT_2);

}
