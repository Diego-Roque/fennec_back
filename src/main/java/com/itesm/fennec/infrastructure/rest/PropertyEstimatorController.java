package com.itesm.fennec.infrastructure.rest;


import com.itesm.fennec.application.useCase.EstimarValorUseCase;
import com.itesm.fennec.domain.model.PropertyEstimator;
import com.itesm.fennec.domain.model.PredictionResult;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("api/estimar")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Estimaciones", description = "Estimación del valor de propiedades (casas y departamentos)")
public class PropertyEstimatorController {




    @Inject
    EstimarValorUseCase estimarValorUseCase;
    @POST
    @Path("/departamento")
    @Operation(summary = "Estimar valor de un departamento")
    @APIResponse(
            responseCode = "200",
            description = "Estimación generada exitosamente",
            content = @Content(schema = @Schema(implementation = PredictionResult.class))
    )
    @APIResponse(
            responseCode = "400",
            description = "Parámetros inválidos"
    )
    @APIResponse(
            responseCode = "500",
            description = "Error interno al procesar la estimación"
    )
    public Response estimarValor(@RequestBody(
                                             description = "Datos del departamento a estimar",
                                             required = true,
                                             content = @Content(schema = @Schema(implementation = PropertyEstimator.class))
                                     )
                                     PropertyEstimator request
    ) {
        PredictionResult result = estimarValorUseCase.execute(request);
        return Response.ok(result).build();
    }

    @POST
    @Path("/casa")
    @Operation(summary = "Estimar valor de una casa")
    @APIResponse(
            responseCode = "200",
            description = "Estimación generada exitosamente",
            content = @Content(schema = @Schema(implementation = PredictionResult.class))
    )
    @APIResponse(
            responseCode = "400",
            description = "Parámetros inválidos"
    )
    @APIResponse(
            responseCode = "500",
            description = "Error interno al procesar la estimación"
    )
    public Response estimarValorCasa(@RequestBody(
                                                 description = "Datos de la casa a estimar",
                                                 required = true,
                                                 content = @Content(schema = @Schema(implementation = PropertyEstimator.class))
                                         )
                                         PropertyEstimator request
    ) {
      PredictionResult result = estimarValorUseCase.executeHouse(request);
      return Response.ok(result).build();
    }
}
