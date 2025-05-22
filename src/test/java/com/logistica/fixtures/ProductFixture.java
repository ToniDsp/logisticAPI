package com.logistica.fixtures;

import com.logistica.data.model.Product;

import java.util.List;

import static com.logistica.fixtures.CategoryFixture.TEST_CATEGORY_1;
import static com.logistica.fixtures.CategoryFixture.TEST_CATEGORY_2;

public class ProductFixture {

    public static Product TEST_PRODUCT_1 = new Product(
            1L,
            "Zanahoria",
            1L,
            1500,
            "Unidades",
            TEST_CATEGORY_1
    );
    public static Product TEST_PRODUCT_2 = new Product(
            2L,
            "Freeway",
            1L,
            986,
            "Unidades",
            TEST_CATEGORY_2
    );

    public static List<Product> TEST_PRODUCTS = List.of(
            TEST_PRODUCT_1, TEST_PRODUCT_2
    );

}
