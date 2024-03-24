package com.example.newsletteremailsender.domain.newsletter.model;

import com.example.newsletteremailsender.domain.exception.NewsletterCronSendingFrequencyInvalidException;
import com.example.newsletteremailsender.domain.exception.NewsletterTagsInvalidException;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.util.Strings;

import java.util.Collection;
import java.util.List;

@Getter
public class Newsletter {

    private String id;

    private Collection<String> tags;

    private String cronSendingFrequency;

    @Setter
    private List<Template> templates;

    public Newsletter(String id, Collection<String> tags, String cronSendingFrequency) {

        this(tags, cronSendingFrequency);
        this.id = id;
    }

    public Newsletter(Collection<String> tags, String cronSendingFrequency) {

        validateNewsletterData(tags, cronSendingFrequency);

        this.tags = tags;
        this.cronSendingFrequency = cronSendingFrequency;
    }

    private void validateNewsletterData(Collection<String> tags, String cronSendingFrequency) {

        validateTags(tags);
        validateCronSendingFrequency(cronSendingFrequency);
    }

    private void validateTags(Collection<String> tags) {

        if (CollectionUtils.isEmpty(tags)) {
            throw new NewsletterTagsInvalidException();
        }
    }

    private void validateCronSendingFrequency(String cronSendingFrequency) {

        if (Strings.isBlank(cronSendingFrequency)) {
            throw new NewsletterCronSendingFrequencyInvalidException();
        }
    }
}

