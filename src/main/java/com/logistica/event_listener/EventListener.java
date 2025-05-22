package com.logistica.event_listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

import java.util.List;

public class EventListener {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final String queueName;
    protected ObjectMapper objectMapper;
    private SqsClient sqsClient;

    public EventListener(String queueName, SqsClient sqsClient, ObjectMapper objectMapper) {
        this.queueName = queueName;
        this.sqsClient = sqsClient;
        this.objectMapper = objectMapper;
    }

    protected void onMessageReceived(String rawEvent) throws JsonProcessingException {
        throw new NotImplementedException("This method should be overriden");
    }

    protected void consumeMessages() {
        try {
            String queueUrl = getQueueUrl();

            var eventsData = readEvent(queueUrl);

            if (eventsData.isEmpty()) {
                log.info("No events in the queue");
                return;
            }

            for (EventData eventData : eventsData) {
                deleteEventFromQueue(eventData.receiptHandle, queueUrl);

                onMessageReceived(eventData.rawEvent);
            }
        } catch (Exception e) {
            log.error("Error reading from queue", e);
        }
    }

    private void deleteEventFromQueue(String receiptHandle, String queueUrl) {
        var deleteRequest = DeleteMessageRequest.builder()
                .queueUrl(queueUrl)
                .receiptHandle(receiptHandle)
                .build();
        sqsClient.deleteMessage(deleteRequest);
    }

    private String getQueueUrl() {
        var getQueueUrlRequest = GetQueueUrlRequest.builder().queueName(queueName).build();
        return sqsClient.getQueueUrl(getQueueUrlRequest).queueUrl();
    }

    private List<EventData> readEvent(String queueUrl) throws JsonProcessingException {
        var receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl(queueUrl)
                .maxNumberOfMessages(10)
                .build();
        ReceiveMessageResponse response = sqsClient.receiveMessage(receiveMessageRequest);
        List<Message> messages = response.messages();
        if (messages.isEmpty()) {
            return List.of();
        }

        return messages.stream()
                .map(message -> new EventData(message.receiptHandle(), message.body()))
                .toList();
    }

    @AllArgsConstructor
    private class EventData {
        public final String receiptHandle;
        public final String rawEvent;
    }
}
