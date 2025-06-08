package com.itesm.fennec.infrastructure.rest;

import com.itesm.fennec.application.service.PropertyEstimatorService;
import com.itesm.fennec.application.useCase.EstimarValorUseCase;
import com.itesm.fennec.domain.model.PropertyEstimator;
import com.itesm.fennec.domain.model.PredictionResult;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("api/estimar")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PropertyEstimatorController {




    @Inject
    EstimarValorUseCase estimarValorUseCase;
    @POST
    @Path("/departamento")
    public Response estimarValor(PropertyEstimator request) {
        PredictionResult result = estimarValorUseCase.execute(request);
        return Response.ok(result).build();
    }

    @POST
    @Path("/casa")
    public Response estimarValorCasa(PropertyEstimator request) {
      PredictionResult result = estimarValorUseCase.executeHouse(request);
      return Response.ok(result).build();
    }
}
