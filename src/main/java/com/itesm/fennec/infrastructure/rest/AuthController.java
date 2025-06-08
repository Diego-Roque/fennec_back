package com.itesm.fennec.infrastructure.rest;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.itesm.fennec.application.useCase.auth.SignUpUseCase;
import com.itesm.fennec.domain.model.User;
import com.itesm.fennec.domain.repository.UserRepository;
import com.itesm.fennec.infrastructure.dto.LoginDTO;
import com.itesm.fennec.infrastructure.dto.auth.SignUpDTO;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

import java.util.HashMap;
import java.util.Map;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Auth", description = "Endpoints para autenticación de usuarios")
public class AuthController {

    @Inject
    SignUpUseCase signUpUseCase;

    @Inject
    UserRepository userRepository;

    @POST
    @Path("/signup")
    @Operation(summary = "Registrar nuevo usuario", description = "Permite registrar un nuevo usuario en el sistema")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Usuario registrado correctamente"),
            @APIResponse(responseCode = "400", description = "Datos inválidos o error de negocio")
    })
    public Response signUp(SignUpDTO signUpDTO) {
        try {
            signUpUseCase.execute(signUpDTO);
            Map<String, String> response = new HashMap<>();
            response.put("message", "User created successfully");
            return Response.ok().entity(response).build();
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
        }
    }

    @POST
    @Path("/login")
    @Operation(summary = "Login de usuario", description = "Verifica si el usuario existe en base a su Firebase UID")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Inicio de sesión exitoso"),
            @APIResponse(responseCode = "401", description = "Usuario no encontrado o error de autenticación")
    })
    public Response login(LoginDTO loginDTO) {
        try {
            User user = userRepository.findByFirebaseUid(loginDTO.getFirebaseId());

            if (user == null) {
                throw new RuntimeException("User not found");
            }

            Map<String, String> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("email", user.getEmail());
            response.put("nombre", user.getNombre());
            response.put("uid", user.getFirebaseId());

            return Response.ok().entity(response).build();
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Account not found in the system. Please sign up first.");
            return Response.status(Response.Status.UNAUTHORIZED).entity(error).build();
        }
    }

    @POST
    @Path("/delete/user")
    @Operation(summary = "Eliminar (desactivar) usuario", description = "Marca un usuario como inactivo usando su token de Firebase")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Usuario eliminado correctamente"),
            @APIResponse(responseCode = "401", description = "Token inválido"),
            @APIResponse(responseCode = "500", description = "Error interno al eliminar")
    })
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
                    .entity("{\"error\":\"Token inválido\"}").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\":\"Error al marcar el usuario como inactivo\"}").build();
        }
    }

    @POST
    @Path("/google")
    @Operation(summary = "Registro con Google", description = "Permite registrar usuarios mediante autenticación de Google")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Usuario registrado con Google exitosamente"),
            @APIResponse(responseCode = "401", description = "Token inválido")
    })
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
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid token").build();
        }
    }
}
