package com.itesm.fennec.application.service;

import com.itesm.fennec.domain.model.Email;
import com.itesm.fennec.domain.repository.EmailRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class EmailService {

    @Inject
    EmailRepository emailRepository;

    public void sendEmail(Email email) throws Exception {
        emailRepository.sendEmail(email);
    }
}
