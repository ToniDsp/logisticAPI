package com.logistica.event_listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistica.domain.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;

@Service
@Profile("!test")
public class TruckEventListener extends EventListener {
    @Autowired
    private TruckService truckService;

    public TruckEventListener(
            @Value("${aws.queue-name}") String queueName,
            @Autowired SqsClient sqsClient,
            @Autowired ObjectMapper objectMapper
    ) {
        super(queueName, sqsClient, objectMapper);

    }

}
