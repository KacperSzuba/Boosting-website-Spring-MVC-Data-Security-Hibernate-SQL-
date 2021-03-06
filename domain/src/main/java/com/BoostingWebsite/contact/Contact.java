package com.BoostingWebsite.contact;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@DynamicUpdate
class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, message = "Name length should be at least 2 letters")
    private String name;

    @NotEmpty(message = "E-mail cannot be empty")
    @Email(message = "Invalid email")
    private String email;

    @Size(min = 4, message = "Subject length should be at least 4 letters")
    private String subject;

    @Column(length = 1000)
    @NotEmpty(message = "Question cannot be empty")
    private String question;

    private LocalDateTime date;

    @PersistenceConstructor
    public Contact() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    void setSubject(String subject) {
        this.subject = subject;
    }

    public String getQuestion() {
        return question;
    }

    void setQuestion(String question) {
        this.question = question;
    }

    public LocalDateTime getDate() {
        return date;
    }

    void setDate(LocalDateTime date) {
        this.date = date;
    }

    @PrePersist
    public void prePersist(){
        date = LocalDateTime.now();
    }
}
