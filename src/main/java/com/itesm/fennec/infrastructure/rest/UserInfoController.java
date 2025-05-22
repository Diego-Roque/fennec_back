package com.itesm.fennec.infrastructure.rest;


import com.itesm.fennec.application.service.UserService;
import com.itesm.fennec.application.service.firebase.FirebaseUserService;
import com.itesm.fennec.domain.model.User;
import com.itesm.fennec.infrastructure.dto.UserDTO;
import com.itesm.fennec.infrastructure.persistence.mapper.UserMapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/api/profile")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserInfoController {
    @Inject
    FirebaseUserService firebaseUserService;

    @Inject
    UserService userService;

    @GET
    public Response getProfile(@HeaderParam("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Falta token").build();
        }

        String token = authHeader.substring(7);
        String uid;


        try {
            uid = firebaseUserService.getUidFromToken(token);

        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Token inválido").build();
        }

        User user = userService.getUserinfoByUid(uid);
        UserDTO userDTO = UserMapper.toDTO(user);

        if (user == null) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("User not found").build();
        }
        return Response.ok(userDTO).build();
    }
}
