package com.dam.flashcards.dto;

import java.io.Serializable;

public class EmailDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String fromEmail;
    private String fromName;
    private String replyTo;
    private String to;
    private String subject;
    private String body;
    private String contentType;

    public EmailDTO() {
    }

    public EmailDTO(String fromEmail, String fromName, String replyTo, String to, String subject, String body,
            String contentType) {
        this.fromEmail = fromEmail;
        this.fromName = fromName;
        this.replyTo = replyTo;
        this.to = to;
        this.subject = subject;
        this.body = body;
        this.contentType = contentType;
    }

    public String getFromEmail() {
        return this.fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getFromName() {
        return this.fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getReplyTo() {
        return this.replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public String getTo() {
        return this.to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public EmailDTO fromEmail(String fromEmail) {
        setFromEmail(fromEmail);
        return this;
    }

    public EmailDTO fromName(String fromName) {
        setFromName(fromName);
        return this;
    }

    public EmailDTO replyTo(String replyTo) {
        setReplyTo(replyTo);
        return this;
    }

    public EmailDTO to(String to) {
        setTo(to);
        return this;
    }

    public EmailDTO subject(String subject) {
        setSubject(subject);
        return this;
    }

    public EmailDTO body(String body) {
        setBody(body);
        return this;
    }

    public EmailDTO contentType(String contentType) {
        setContentType(contentType);
        return this;
    }
}
