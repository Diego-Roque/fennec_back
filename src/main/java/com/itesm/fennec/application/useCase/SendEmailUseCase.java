package com.itesm.fennec.application.useCase;

import com.itesm.fennec.application.service.EmailService;
import com.itesm.fennec.application.service.UserService;
import com.itesm.fennec.domain.model.Email;
import com.itesm.fennec.infrastructure.dto.SendEmailDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SendEmailUseCase {

    @Inject
    UserService userService;

    @Inject
    EmailService emailService;

    public void execute(SendEmailDTO sendEmailDTO) throws Exception {
        String email = userService.getUserEmailByFirebaseId(sendEmailDTO.getFirebaseUID());
        Email emailObj = new Email(email, sendEmailDTO.getSubject(), sendEmailDTO.getMessage());
        emailService.sendEmail(emailObj);
    }
}
