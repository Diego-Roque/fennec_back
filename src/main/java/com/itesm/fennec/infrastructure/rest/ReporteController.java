package com.itesm.fennec.infrastructure.rest;

import com.itesm.fennec.application.service.ReporteService;
import com.itesm.fennec.domain.model.InformeValuacion;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api")
public class ReporteController {

    @Inject
    ReporteService reporteService;

    @POST
    @Path("/create-new-report")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response generarReporte(InformeValuacion informe) {
        try {
            reporteService.generar(informe);
            return Response.ok().entity("{\"mensaje\": \"Reporte generado exitosamente\"}").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error al generar el reporte\"}").build();
        }
    }
}
