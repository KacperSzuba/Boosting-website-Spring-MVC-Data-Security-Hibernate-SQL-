package com.BoostingWebsite.contact;

import java.time.LocalDateTime;

import static com.BoostingWebsite.utils.EmailValidator.whetherEmailIsValid;

class Contact {
    private Long id;
    //@Size(min = 2, message = "Name length should be at least 2 letters")
    private String name;
    //@NotEmpty(message = "E-mail cannot be empty")
    //@Email(message = "Invalid email")
    private String email;
    //@Size(min = 4, message = "Subject length should be at least 4 letters")
    private String subject;
    //@Column(length = 1000)
   //@NotEmpty(message = "Question cannot be empty")
    private String question;
    private LocalDateTime date;

    public Contact() {
    }

    private Contact(Long id, String name, String email, String subject, String question, LocalDateTime date) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.question = question;
        this.date = date;
    }

    boolean canCreate(){
        return whetherEmailIsValid(email);
    }

    void create(){
        this.date = LocalDateTime.now();
    }

    static Contact restore(ContactSnapshot snapshot){
        return new Contact(
                snapshot.getId(),
                snapshot.getName(),
                snapshot.getEmail(),
                snapshot.getSubject(),
                snapshot.getQuestion(),
                snapshot.getDate());
    }

    ContactSnapshot getSnapshot(){
        return new ContactSnapshot(id, name, email, subject, question, date);
    }
}
