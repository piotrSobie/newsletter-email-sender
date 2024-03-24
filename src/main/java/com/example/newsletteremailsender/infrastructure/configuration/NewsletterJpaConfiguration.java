package com.example.newsletteremailsender.infrastructure.configuration;

import com.example.newsletteremailsender.infrastructure.repository.newsletter.SpringDataJpaNewsletterRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackageClasses = SpringDataJpaNewsletterRepository.class)
public class NewsletterJpaConfiguration {
}
