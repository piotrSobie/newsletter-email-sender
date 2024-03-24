package com.example.newsletteremailsender.infrastructure.repository.newsletter;

import com.example.newsletteremailsender.domain.newsletter.model.Newsletter;
import com.example.newsletteremailsender.domain.newsletter.repository.NewsletterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class JpaNewsletterRepository implements NewsletterRepository {

    private final SpringDataJpaNewsletterRepository newsletterRepository;

    @Autowired
    public JpaNewsletterRepository(SpringDataJpaNewsletterRepository newsletterRepository) {

        this.newsletterRepository = newsletterRepository;
    }

    @Override
    @Transactional
    public Optional<Newsletter> findNewsletterById(String newsletterId) {

        Optional<NewsletterEntity> newsletterOptional = newsletterRepository.findById(newsletterId);
        return newsletterOptional.map(JpaNewsletterMapper.getMapper::newsletterEntityToNewsletter);
    }
}
