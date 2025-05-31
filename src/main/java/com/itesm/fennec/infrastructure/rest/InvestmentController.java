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

import java.util.List;
import java.util.Optional;

@Path("api/investment")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InvestmentController {
    @Inject
    InsertarInversionUseCase insertarInversionUseCase;


    @POST
    public Response insertarInversion(Investment investment) {
        investment = insertarInversionUseCase.execute(investment);
        return Response.ok(investment).build();
    }

    @Inject
    FirebaseUserService firebaseUserService;

    @Inject
    ListInvestmentsUseCase listInvestmentsUseCase;

    @GET
    @Path("list-investments")
    public Response listInvestments(@HeaderParam("Authorization") String authHeader) {
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
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity("No Investment found").build();
        }


    }
}
