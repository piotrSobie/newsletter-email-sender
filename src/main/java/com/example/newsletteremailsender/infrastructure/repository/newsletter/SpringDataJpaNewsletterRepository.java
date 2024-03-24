package com.example.newsletteremailsender.infrastructure.repository.newsletter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SpringDataJpaNewsletterRepository extends JpaRepository<NewsletterEntity, String> {
}
