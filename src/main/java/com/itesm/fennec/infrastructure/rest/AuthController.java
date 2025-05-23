package com.itesm.fennec.infrastructure.rest;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.itesm.fennec.application.useCase.auth.SignUpUseCase;
import com.itesm.fennec.domain.model.User;
import com.itesm.fennec.domain.repository.UserRepository;
import com.itesm.fennec.infrastructure.dto.auth.SignUpDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Path("/auth")
public class AuthController {
    @Inject
    SignUpUseCase signUpUseCase;

    @POST
    @Path("/signup")
    public Response signUp(SignUpDTO signUpDTO) {
        try {
            signUpUseCase.execute(signUpDTO);
            Map<String, String> response = new HashMap<>();
            response.put("message", "User created successfully");
            return Response.ok().entity(response).build();
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(error)
                    .build();
        }
    }
    @POST
    @Path("/delete/user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@Context HttpHeaders headers) {
        String authHeader = headers.getHeaderString("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"error\":\"Authorization header missing or invalid\"}")
                    .build();
        }

        String token = authHeader.substring(7);
        try {
            FirebaseToken firebaseToken = FirebaseAuth.getInstance().verifyIdToken(token);
            String uid = firebaseToken.getUid();
            userRepository.deleteUser(uid);

            return Response.ok("{\"message\":\"Usuario marcado como inactivo correctamente\"}").build();

        } catch (FirebaseAuthException e) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"error\":\"Token inválido\"}")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\":\"Error al marcar el usuario como inactivo\"}")
                    .build();
        }
    }


    @Inject
    UserRepository userRepository;

    @POST
    @Path("/google")
    public Response registerWithGoogle(@Context HttpHeaders headers) {
        String idToken = headers.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (idToken == null || !idToken.startsWith("Bearer ")) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        idToken = idToken.substring(7); // remove "Bearer "

        try {
            FirebaseToken firebaseToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            String uid = firebaseToken.getUid();
            String email = firebaseToken.getEmail();
            String name = firebaseToken.getName();


            User newUser = new User();
            newUser.setFirebaseId(uid);
            newUser.setEmail(email);
            newUser.setNombre(name);
            userRepository.createUser(newUser);


            return Response.ok().build();
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid token").build();
        }
    }
}
