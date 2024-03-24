package com.example.newsletteremailsender.domain.port;

public interface EmailSender {

    void sendEmail(String toEmail, String subject, String body);
}
