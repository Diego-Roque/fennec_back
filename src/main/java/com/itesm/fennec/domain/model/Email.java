package com.itesm.fennec.domain.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Email {
    private String to;
    private String subject;
    private String message;

    public Email(String to, String subject, String message) {
        this.to = to;
        this.subject = subject;
        this.message = message;
    }

}
