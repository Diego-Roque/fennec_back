package com.itesm.fennec.infrastructure.rest;

import com.itesm.fennec.application.useCase.InsertarInversionUseCase;
import com.itesm.fennec.domain.model.Investment;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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

}
