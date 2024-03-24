package com.example.newsletteremailsender.domain.newsletter.service;

import com.example.newsletteremailsender.domain.newsletter.model.Newsletter;
import com.example.newsletteremailsender.domain.newsletter.model.NewsletterTaskData;
import com.example.newsletteremailsender.domain.newsletter.model.Template;
import com.example.newsletteremailsender.domain.newsletter.repository.NewsletterRepository;
import com.example.newsletteremailsender.domain.port.EmailSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsletterService {

    private final NewsletterRepository newsletterRepository;
    private final EmailSender emailSender;

    public Optional<Newsletter> getNewsletterById(String newsletterId) {

        return newsletterRepository.findNewsletterById(newsletterId);
    }

    public void sendEmail(NewsletterTaskData newsletterTaskData) {

        Optional<Newsletter> newsletterOptional = getNewsletterById(newsletterTaskData.getNewsletterId());
        if (newsletterOptional.isEmpty()) {
            log.error("Newsletter {} not found. Execution finished.", newsletterTaskData.getNewsletterId());
            return;
        }

        Newsletter newsletter = newsletterOptional.get();
        Optional<Template> templateOptional = newsletter.getTemplates().stream()
                .filter(template -> template.getChannels().stream()
                        .anyMatch(channel -> channel.equalsIgnoreCase("email"))
                )
                .findFirst();

        if (templateOptional.isEmpty()) {
            log.info("Newsletter {} does not have template for emails. Execution finished.", newsletter.getId());
            return;
        }

        Template template = templateOptional.get();
        String text = template.getText();

        newsletterTaskData.getSubscriptionIds().forEach(subscriptionId -> {
            String email = ""; // todo get email using subscriptionId
            emailSender.sendEmail(email, "testSubject", text);
        });
    }
}
