package com.itesm.fennec.application.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.itesm.fennec.domain.model.User;
import com.itesm.fennec.domain.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SignUpService {
    @Inject
    UserRepository userRepository;

    public User signUp(String email, String password, String nombre, String telefono, String tipoRole) throws Exception {
        CreateRequest request = new CreateRequest()
                .setEmail(email)
                .setPassword(password)
                .setEmailVerified(false)
                .setDisabled(false);

        UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);

        User user = new User();
        user.setFirebaseId(userRecord.getUid());
        user.setNombre(nombre);
        user.setEmail(email);
        user.setTelefono(telefono);
        user.setTipoRole(tipoRole);
        return userRepository.createUser(user);
    }
}
