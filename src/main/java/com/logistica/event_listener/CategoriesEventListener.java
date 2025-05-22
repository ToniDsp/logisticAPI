package com.logistica.event_listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistica.data.model.Categories;
import com.logistica.data.model.Truck;
import com.logistica.data.model.Warehouse;
import com.logistica.data.model.events.category.CreateCategoryEvent;
import com.logistica.data.model.events.truck.CreateTruckEvent;
import com.logistica.data.model.events.warehouse.CreateWarehouseEvent;
import com.logistica.domain.CategoriesService;
import com.logistica.domain.TruckService;
import com.logistica.domain.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;

@Service
@Profile("!test")
public class CategoriesEventListener extends EventListener {

    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private TruckService truckService;
    @Autowired
    private WarehouseService warehouseService;

    public CategoriesEventListener(
            @Value("${aws.queue-name}") String queueName,
            @Autowired SqsClient sqsClient,
            @Autowired ObjectMapper objectMapper
    ) {
        super(queueName, sqsClient, objectMapper);
    }


    @Override
    @Scheduled(fixedDelay = 2000) //Tiempo en leer el siguientem mensaje
    @Async
    protected void consumeMessages() {
        super.consumeMessages();
    }

    @Override
    protected void onMessageReceived(String rawEvent) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(rawEvent);
        if (jsonNode.has("eventName")) {
            String eventName = jsonNode.get("eventName").asText();

            switch (eventName) { //Lee el eventName del .json
                case "create-category":
                    CreateCategoryEvent eventCategory = objectMapper.treeToValue(jsonNode, CreateCategoryEvent.class);
                    categoriesService.save(new Categories(jsonNode.get("categoryName").asText()));
                    System.out.println("New category event read. " + eventName + " and processed");
                    break;
                case "create-truck":
                    CreateTruckEvent eventTruck = objectMapper.treeToValue(jsonNode, CreateTruckEvent.class);
                    truckService.save(new Truck(jsonNode.get("truckPlate").asText()));
                    System.out.println("New truck event read. " + eventName + " and processed");
                    break;
                case "create-warehouse":
                    CreateWarehouseEvent eventWarehouse = objectMapper.treeToValue(jsonNode, CreateWarehouseEvent.class);
                    warehouseService.save(new Warehouse(jsonNode.get("name_warehouse").asText(), jsonNode.get("manager").asText()));
                    System.out.println("New warehouse event read. " + eventName + " and processed");
                    break;

                default:
                    throw new IllegalArgumentException("Unsupported event type");
            }


        }
    }
}
