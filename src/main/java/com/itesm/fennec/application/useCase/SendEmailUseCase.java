// SendEmailUseCase.java
package com.itesm.fennec.application.useCase;

import com.itesm.fennec.domain.model.Email;
import com.itesm.fennec.domain.repository.EmailRepository;
import com.itesm.fennec.infrastructure.dto.SendEmailDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SendEmailUseCase {

    @Inject
    EmailRepository emailRepository;

    public void execute(SendEmailDTO sendEmailDTO) throws Exception {
        Email email = new Email(sendEmailDTO.getTo(), sendEmailDTO.getSubject(), sendEmailDTO.getMessage());
        emailRepository.sendEmail(email);
    }
}
