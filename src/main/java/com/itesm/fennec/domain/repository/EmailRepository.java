package com.itesm.fennec.domain.repository;

import com.itesm.fennec.domain.model.Email;

public interface EmailRepository {
    void sendEmail(Email email) throws Exception;
}
