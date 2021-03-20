package com.BoostingWebsite.contact;

import java.time.LocalDateTime;

class ContactSnapshot {
    private Long id;
    private String name;
    private String email;
    private String subject;
    private String question;
    private LocalDateTime date;

    protected ContactSnapshot(){}

    ContactSnapshot(Long id, String name, String email, String subject, String question, LocalDateTime date) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.question = question;
        this.date = date;
    }

    Long getId() {
        return id;
    }

    String getName() {
        return name;
    }

    String getEmail() {
        return email;
    }

    String getSubject() {
        return subject;
    }

    String getQuestion() {
        return question;
    }

    LocalDateTime getDate() {
        return date;
    }
}
