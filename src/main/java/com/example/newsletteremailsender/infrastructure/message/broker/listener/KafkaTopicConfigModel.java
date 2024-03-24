package com.example.newsletteremailsender.infrastructure.message.broker.listener;

import lombok.Data;

@Data
public class KafkaTopicConfigModel {

    private String topicName;
    private String groupId;
}
