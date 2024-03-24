package com.example.newsletteremailsender.infrastructure.message.broker.listener;

import com.example.newsletteremailsender.domain.newsletter.model.NewsletterTaskData;
import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@EnableKafka
@Configuration
@ConfigurationProperties(prefix = "kafka.consumer")
public class KafkaMessageConsumerConfig {

    public static final String DEFAULT_JSON_CONSUMER_BEAN = "defaultJsonConsumerKafkaTemplate";

    private Map<String, String> defaultJsonConsumer;

    @Bean(DEFAULT_JSON_CONSUMER_BEAN)
    public ConcurrentKafkaListenerContainerFactory<String, NewsletterTaskData> kafkaJsonListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, NewsletterTaskData> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(prepareConfig(defaultJsonConsumer)));

        return factory;
    }

    public ConsumerFactory<String, NewsletterTaskData> consumerFactory(Map<String, Object> props) {

        final JsonDeserializer<NewsletterTaskData> jsonDeserializer = new JsonDeserializer<>(NewsletterTaskData.class);
        jsonDeserializer.addTrustedPackages("*");
        jsonDeserializer.setUseTypeHeaders(false);

        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), jsonDeserializer);
    }

    private Map<String, Object> prepareConfig(Map<String, String> kafkaConsumerConfig) {

        return new HashMap<>(kafkaConsumerConfig);
    }
}

