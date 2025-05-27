package com.itesm.fennec.infrastructure.rest;


import com.itesm.fennec.application.useCase.EstimarValorUseCase;
import com.itesm.fennec.domain.model.PropertyEstimator;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/api/propertyEstimator")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PropertyEstimatorController {
    @Inject
    EstimarValorUseCase estimarValor;

    @Path("/departamento")
    @POST
    public Response estimarPropiedad(PropertyEstimator estimator) {
       estimator = estimarValor.execute(estimator);
        return Response.ok().entity(estimator).build();
    }

    @Path("/casa")
    @POST
    public Response estimarPropiedadCasa(PropertyEstimator estimator) {
       estimator = estimarValor.executeCasa(estimator);
       return Response.ok().entity(estimator).build();
    }
}
