package com.example.newsletteremailsender.infrastructure.message.broker.listener;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "kafka.listeners")
public class KafkaListenersConfigProperties {

    private KafkaTopicConfigModel scheduleEvent;
}
