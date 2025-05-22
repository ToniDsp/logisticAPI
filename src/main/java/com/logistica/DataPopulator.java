package com.logistica;

import com.logistica.controller.contract.ShipmentCreationRequest;
import com.logistica.data.model.*;
import com.logistica.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Profile("!test")
public class DataPopulator implements CommandLineRunner {

    @Autowired
    private ProductService productService;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private TruckService truckService;
    @Autowired
    private ShipmentService shipmentService;
    @Autowired
    private ProviderService providerService;
    @Autowired
    private CategoriesService categoriesService;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        var catVegetFries = categoriesService.save(new Categories("Vegetable fries"));
        var catVegetSalad = categoriesService.save(new Categories("Vegetable salad"));
        var catFishSea = categoriesService.save(new Categories("Fish sea"));
        var catSauce = categoriesService.save(new Categories("Sauce"));
        var catLiquid = categoriesService.save(new Categories("Liquids"));

        var p1 = productService.save(new Product("Brocoli", 1L, 23.2, "Kg", catVegetFries));
        var p2 = productService.save(new Product("Tomatoes", 1L, 23, "Kg", catVegetSalad));
        var p3 = productService.save(new Product("Fish", 2L, 5, "Kg", catFishSea));
        var p4 = productService.save(new Product("cesar sauce", 3L, 25, "Un", catSauce));
        var p5 = productService.save(new Product("Coke", 1L, 44, "Un", catLiquid));


        truckService.save(new Truck("4685MCA"));
        truckService.save(new Truck("7959JAD"));

        var allProducts = productService.findAll();
        warehouseService.save(new Warehouse("Madrid", "Jose Felix Garcia", allProducts));


        providerService.save(new Provider("Consum", "Josebas@consum.es", List.of(p1, p2, p5)));
        providerService.save(new Provider("Dependencias Rodriguez", "DependenciasMercantiles@mgmail.es", List.of(p3)));
        providerService.save(new Provider("Hacendado", "Hacendado@mercadona.es", List.of(p4)));
        shipmentService.create(
                new ShipmentCreationRequest(1L, 1L, List.of(1L))
        );


    }
}

