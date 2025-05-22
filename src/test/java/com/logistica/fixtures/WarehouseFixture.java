package com.logistica.fixtures;

import com.logistica.data.model.Warehouse;

import static com.logistica.fixtures.ProductFixture.TEST_PRODUCTS;

public class WarehouseFixture {

    public static Warehouse TEST_WAREHOUSE = new Warehouse(
            1L,
            "Alcalá",
            "joseluis@logistica.com",
            TEST_PRODUCTS
    );

}
