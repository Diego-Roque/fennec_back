package com.itesm.fennec.infrastructure.rest;

import com.itesm.fennec.domain.model.PropertyList;
import com.itesm.fennec.application.service.PropertyListService;
import com.itesm.fennec.infrastructure.dto.PropertyListDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/propiedades")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PropertyListController {

    @Inject
    PropertyListService propertyListService;

    @POST
    @Path("/filtrar")
    public Response filtrarPropiedades(PropertyListDTO request) {
        PropertyList result = propertyListService.filtrarPropiedades(
                request.getTipoPropiedad(),
                request.toMap(),
                request.getPagina(),
                request.getLimite()
        );
        return Response.ok(result).build();
    }
}
