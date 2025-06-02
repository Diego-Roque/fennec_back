package com.itesm.fennec.application.useCase;

import com.itesm.fennec.application.service.firebase.FirebaseUserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetUidFromTokenUseCase {

    @Inject
    FirebaseUserService firebaseUserService;

    public String execute(String token){
        return firebaseUserService.getUidFromToken(token);
    }
}
