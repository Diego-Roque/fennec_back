package com.itesm.fennec.infrastructure.rest;

import com.itesm.fennec.application.useCase.InsertarInformacionValuacionUseCase;
import com.itesm.fennec.domain.model.InformeValuacion;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReporteController {

    @Inject
    InsertarInformacionValuacionUseCase insertarInformacionValuacionUseCase;

    @POST
    @Path("/create-new-report")
    public Response generarReporte(InformeValuacion informe) {
        try {
            insertarInformacionValuacionUseCase.execute(informe);
            return Response.ok().entity("{\"mensaje\": \"Reporte generado exitosamente\"}").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error al generar el reporte\"}").build();
        }
    }
}