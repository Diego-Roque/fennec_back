package com.itesm.fennec.infrastructure.rest;

import com.itesm.fennec.application.useCase.GetFilteredPropertyUseCase;
import com.itesm.fennec.domain.model.PropertyList;
import com.itesm.fennec.application.service.PropertyListService;
import com.itesm.fennec.infrastructure.dto.PropertyListDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/propiedades")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PropertyListController {

    @Inject
    GetFilteredPropertyUseCase getFilteredPropertyUseCase;

    @POST
    @Path("/filtrar")
    @Operation(summary = "Filtrar propiedades", description = "Filtra propiedades por tipo, rango de precios, superficie, etc.")
    @APIResponse(
            responseCode = "200",
            description = "Lista de propiedades filtradas",
            content = @Content(schema = @Schema(implementation = PropertyList.class))
    )
    @APIResponse(
            responseCode = "400",
            description = "Solicitud malformada o con datos inválidos"
    )
    @APIResponse(
            responseCode = "500",
            description = "Error interno al realizar el filtrado"
    )
    public Response filtrarPropiedades(@RequestBody(
                                                   description = "Parámetros para el filtrado de propiedades",
                                                   required = true,
                                                   content = @Content(schema = @Schema(implementation = PropertyListDTO.class))
                                           )
                                           PropertyListDTO request
    ) {
        PropertyList result = getFilteredPropertyUseCase.execute(
                request.getTipoPropiedad(),
                request.toMap(),
                request.getPagina(),
                request.getLimite()
        );
        return Response.ok(result).build();
    }
}
