package com.logistica.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistica.controller.contract.ShipmentCreationRequest;
import com.logistica.controller.contract.ShipmentResponse;
import com.logistica.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.logistica.fixtures.CategoryFixture.TEST_CATEGORY_1;
import static com.logistica.fixtures.CategoryFixture.TEST_CATEGORY_2;
import static com.logistica.fixtures.ProductFixture.TEST_PRODUCT_1;
import static com.logistica.fixtures.ProductFixture.TEST_PRODUCT_2;
import static com.logistica.fixtures.TruckFixture.TEST_TRUCK;
import static com.logistica.fixtures.WarehouseFixture.TEST_WAREHOUSE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ShipmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TruckService truckService;

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private ShipmentService shipmentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetNoShipments() throws Exception {
        mockMvc.perform(get("/shipments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    @DirtiesContext
    void testGetAllShipments() throws Exception {
        saveTestData();
        shipmentService.create(new ShipmentCreationRequest(1L, 1L, List.of(1L)));

        mockMvc.perform(get("/shipments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
        // TODO: Comprobar también que los datos devueltos son correctos
    }

    @Test
    @DirtiesContext
    void createNewShipment() throws Exception {
        saveTestData();

        var request = new ShipmentCreationRequest(1L, 1L, List.of(1L));
        var jsonRequest = objectMapper.writeValueAsString(request);
        mockMvc.perform(post("/shipments").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.truckId").value(1))
                .andExpect(jsonPath("$.warehouseId").value(1))
                .andExpect(jsonPath("$.productIds.length()").value(1))
                .andExpect(jsonPath("$.productIds[0]").value(1));

        var savedShipment = shipmentService.findById(1L);
        assertThat(savedShipment.isPresent()).isTrue();
        assertThat(savedShipment.get().getId()).isEqualTo(1L);
        assertThat(savedShipment.get().getTruck().getId()).isEqualTo(TEST_TRUCK.getId());
        assertThat(savedShipment.get().getTruck().getPlateNumber()).isEqualTo(TEST_TRUCK.getPlateNumber());
        assertThat(savedShipment.get().getWarehouse().getId()).isEqualTo(TEST_WAREHOUSE.getId());
        assertThat(savedShipment.get().getWarehouse().getName()).isEqualTo(TEST_WAREHOUSE.getName());
        assertThat(savedShipment.get().getWarehouse().getManager()).isEqualTo(TEST_WAREHOUSE.getManager());
//        assertThat(savedShipment.get().getProducts().size()).isEqualTo(1);
//        assertThat(savedShipment.get().getProducts()).isEqualTo(List.of(TEST_PRODUCT_1));
    }

    private void saveTestData(){
        categoriesService.save(TEST_CATEGORY_1);
        categoriesService.save(TEST_CATEGORY_2);
        productService.save(TEST_PRODUCT_1);
        productService.save(TEST_PRODUCT_2);
        warehouseService.save(TEST_WAREHOUSE);
        truckService.save(TEST_TRUCK);
    }
}
