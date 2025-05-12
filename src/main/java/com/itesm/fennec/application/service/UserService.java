package com.itesm.fennec.application.service;

import com.itesm.fennec.domain.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserService {
    @Inject 
    UserRepository userRepository;
    
}
