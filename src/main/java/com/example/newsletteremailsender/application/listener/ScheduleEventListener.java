package com.example.newsletteremailsender.application.listener;

import com.example.newsletteremailsender.domain.newsletter.model.NewsletterTaskData;
import com.example.newsletteremailsender.domain.newsletter.service.NewsletterService;
import com.example.newsletteremailsender.infrastructure.message.broker.listener.KafkaMessageConsumerConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleEventListener {

    private final NewsletterService newsletterService;

    @KafkaListener(topics = "#{kafkaListenersConfigProperties.getScheduleEvent().getTopicName()}",
            containerFactory = KafkaMessageConsumerConfig.DEFAULT_JSON_CONSUMER_BEAN
    )
    public void consume(NewsletterTaskData newsletterTaskData) {

        log.info("Received message: {}", newsletterTaskData);
        newsletterService.sendEmail(newsletterTaskData);
    }
}

