package com.itesm.fennec.infrastructure.rest;

import com.itesm.fennec.application.service.CasaService;
import com.itesm.fennec.domain.model.AlcaldiaRequest;
import com.itesm.fennec.domain.model.CasaPrecioPromedioResult;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;



@Path("api/casa")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CasaController {

    @Inject
    CasaService service;

    @POST
    @Path("/promedio")
    public Response obtenerPromedio(AlcaldiaRequest request) {
        String alcaldia = request.getAlcaldia();

        if (alcaldia == null || alcaldia.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("El campo 'alcaldia' es requerido.")
                    .build();
        }

        CasaPrecioPromedioResult result = service.obtenerPromedio(alcaldia);
        return Response.ok(result).build();
    }

    @POST
    @Path("/cantidad")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response contarPorAlcaldia(AlcaldiaRequest request) {
        Long cantidad = service.contarPorAlcaldia(request.getAlcaldia());
        return Response.ok(cantidad).build();
    }

}
