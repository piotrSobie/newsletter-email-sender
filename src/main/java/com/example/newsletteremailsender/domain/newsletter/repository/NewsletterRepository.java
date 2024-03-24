package com.example.newsletteremailsender.domain.newsletter.repository;

import com.example.newsletteremailsender.domain.newsletter.model.Newsletter;

import java.util.Optional;

public interface NewsletterRepository {

    Optional<Newsletter> findNewsletterById(String newsletterId);
}
