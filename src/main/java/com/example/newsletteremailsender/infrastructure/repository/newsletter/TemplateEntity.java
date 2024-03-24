package com.example.newsletteremailsender.infrastructure.repository.newsletter;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "template")
public class TemplateEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "template_id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "newsletter_id", nullable = false)
    private NewsletterEntity newsletter;

    @ElementCollection
    @CollectionTable(name = "template_channel_type", joinColumns = @JoinColumn(name = "template_id"))
    @Column(name = "name")
    private List<String> channels;

    @Column(name = "text")
    private String text;
}
