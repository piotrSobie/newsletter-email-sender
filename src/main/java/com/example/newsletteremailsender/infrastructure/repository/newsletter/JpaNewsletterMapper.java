package com.example.newsletteremailsender.infrastructure.repository.newsletter;

import com.example.newsletteremailsender.domain.newsletter.model.Newsletter;
import com.example.newsletteremailsender.domain.newsletter.model.Template;
import org.apache.commons.collections4.CollectionUtils;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface JpaNewsletterMapper {

    JpaNewsletterMapper getMapper = Mappers.getMapper(JpaNewsletterMapper.class);

    default Newsletter newsletterEntityToNewsletter(NewsletterEntity newsletterEntity) {

        Newsletter newsletter = new Newsletter(newsletterEntity.getId(), newsletterEntity.getTags(), newsletterEntity.getCronSendingFrequency());
        newsletter.setTemplates(templateEntitiesToTemplates(newsletterEntity.getTemplates()));

        afterNewsletterEntityToNewsletter(newsletterEntity, newsletter);

        return newsletter;
    }

    default Template templateEntityToTemplate(TemplateEntity templateEntity) {

        return new Template(templateEntity.getId(), templateEntity.getChannels(), templateEntity.getText(), null);
    }

    default List<Template> templateEntitiesToTemplates(Collection<TemplateEntity> templateEntities) {

        if (CollectionUtils.isEmpty(templateEntities)) {
            return new ArrayList<>();
        }

        return templateEntities.stream()
                .map(this::templateEntityToTemplate)
                .collect(Collectors.toList());
    }

    @AfterMapping
    default void afterNewsletterEntityToNewsletter(NewsletterEntity newsletterEntity, @MappingTarget Newsletter newsletter) {

        if (CollectionUtils.isNotEmpty(newsletterEntity.getTemplates())) {
            newsletter.getTemplates() //
                    .forEach(template -> template.setNewsletter(newsletter));
        }
    }
}
