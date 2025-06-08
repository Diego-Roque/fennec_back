package com.itesm.fennec.infrastructure.rest;

import com.itesm.fennec.application.service.firebase.FirebaseUserService;
import com.itesm.fennec.application.useCase.InsertarInversionUseCase;
import com.itesm.fennec.application.useCase.ListInvestmentsUseCase;
import com.itesm.fennec.domain.model.Investment;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import com.itesm.fennec.domain.repository.InvestmentRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@Path("api/investment")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Investment", description = "Operaciones sobre inversiones de usuario")
public class InvestmentController {
    @Inject
    InsertarInversionUseCase insertarInversionUseCase;


    @POST
    @Operation(summary = "Insertar una nueva inversión para el usuario autenticado")
    @APIResponse(responseCode = "200", description = "Inversión insertada exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Investment.class)))
    @APIResponse(responseCode = "401", description = "Token de autorización faltante o inválido")
    public Response insertarInversion(@Parameter(description = "Token de Firebase", required = true)
                                          @HeaderParam("Authorization") String authHeader,
                                      @RequestBody(description = "Datos de la inversión", required = true,
                                              content = @Content(schema = @Schema(implementation = Investment.class)))
                                          Investment investment) {
        if (authHeader == null || authHeader.isEmpty()) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build();
        }
        String token = authHeader.substring(7);
        String firebaseId = firebaseUserService.getUidFromToken(token);
        System.out.println(firebaseId);
        investment.setId_usuario(firebaseId);

        investment = insertarInversionUseCase.execute(investment);
        return Response.ok(investment).build();
    }



    @Inject
    FirebaseUserService firebaseUserService;

    @Inject
    ListInvestmentsUseCase listInvestmentsUseCase;

    @GET
    @Path("list-investments")
    @Operation(summary = "Listar todas las inversiones del usuario autenticado")
    @APIResponse(responseCode = "200", description = "Listado de inversiones",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Investment.class)))
    @APIResponse(responseCode = "401", description = "Token de autorización faltante o inválido")
    @APIResponse(responseCode = "400", description = "No se encontraron inversiones o error de token")
    public Response listInvestments(@Parameter(description = "Token de Firebase", required = true)
                                        @HeaderParam("Authorization") String authHeader) {
        if (authHeader == null || authHeader.isEmpty()) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build();
        }
        String token = authHeader.substring(7);
        String firebaseId;
        try {
            firebaseId = firebaseUserService.getUidFromToken(token);

            List<Investment> investments = listInvestmentsUseCase.execute(firebaseId);
            return Response.ok(investments).build();

        } catch(Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity("No Investment found").build();
        }


    }
}
