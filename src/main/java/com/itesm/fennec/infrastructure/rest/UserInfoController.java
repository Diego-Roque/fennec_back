package com.itesm.fennec.infrastructure.rest;


import com.itesm.fennec.application.service.UserService;
import com.itesm.fennec.application.service.firebase.FirebaseUserService;
import com.itesm.fennec.application.useCase.ActualizarNumeroUseCase;
import com.itesm.fennec.application.useCase.GetUidFromTokenUseCase;
import com.itesm.fennec.application.useCase.ObtenerRoleUseCase;
import com.itesm.fennec.domain.model.User;
import com.itesm.fennec.infrastructure.dto.PhoneUpdateDTO;
import com.itesm.fennec.infrastructure.dto.UserDTO;
import com.itesm.fennec.infrastructure.persistence.mapper.UserMapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/api/profile")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Perfil de Usuario", description = "Operaciones relacionadas con el perfil del usuario")
public class UserInfoController {
    @Inject
    GetUidFromTokenUseCase getUidFromTokenUseCase;

    @Inject
    UserService userService;

    @Inject
    ActualizarNumeroUseCase actualizarNumeroUseCase;


    @POST
    @Path("/updatephone")
    @Operation(
            summary = "Actualizar número telefónico",
            description = "Permite a un usuario autenticado actualizar su número telefónico"
    )
    @APIResponse(
            responseCode = "200",
            description = "Número actualizado exitosamente",
            content = @Content(schema = @Schema(implementation = User.class))
    )
    @APIResponse(
            responseCode = "400",
            description = "Número no proporcionado"
    )
    @APIResponse(
            responseCode = "401",
            description = "Token no válido o no proporcionado"
    )
    public Response updatePhone(@HeaderParam("Authorization") String authHeader, PhoneUpdateDTO phoneUpdateDTO) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Falta token").build();
        }

        String token = authHeader.substring(7);
        String uid;

        try {
            uid = getUidFromTokenUseCase.execute(token);
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Token inválido").build();
        }

        if (phoneUpdateDTO == null || phoneUpdateDTO.getPhone() == null || phoneUpdateDTO.getPhone().trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Número telefónico no proporcionado").build();
        }

        User updatedUser = actualizarNumeroUseCase.execute(uid, phoneUpdateDTO.getPhone());
        return Response.ok(updatedUser).build();
    }


    @GET
    @Operation(
            summary = "Obtener perfil de usuario",
            description = "Devuelve la información del perfil del usuario autenticado"
    )
    @APIResponse(
            responseCode = "200",
            description = "Perfil obtenido exitosamente",
            content = @Content(schema = @Schema(implementation = UserDTO.class))
    )
    @APIResponse(
            responseCode = "401",
            description = "Token inválido o usuario no encontrado"
    )
    public Response getProfile(@HeaderParam("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Falta token").build();
        }

        String token = authHeader.substring(7);
        String uid;


        try {
            uid = getUidFromTokenUseCase.execute(token);

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

    @Inject
    ObtenerRoleUseCase  obtenerRoleUseCase;

    @GET
    @Path("/get_rol")
    @Operation(
            summary = "Obtener rol del usuario",
            description = "Devuelve el tipo de rol del usuario autenticado (por ejemplo: profesional o básico)"
    )
    @APIResponse(
            responseCode = "200",
            description = "Rol obtenido correctamente",
            content = @Content(schema = @Schema(implementation = User.class))
    )
    @APIResponse(
            responseCode = "401",
            description = "Token inválido o usuario no encontrado"
    )
    public Response getRole(@HeaderParam("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Falta token").build();
        }
        String token = authHeader.substring(7);
        String uid;
        try {
            uid = getUidFromTokenUseCase.execute(token);
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Token inválido").build();
        }
        User user = obtenerRoleUseCase.execute(uid);
        if (user == null) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("User not found").build();
        }
        return Response.ok(user).build();
    }


}
