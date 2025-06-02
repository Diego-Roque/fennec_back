package com.itesm.fennec.infrastructure.rest;

import com.itesm.fennec.application.service.firebase.FirebaseUserService;
import com.itesm.fennec.application.useCase.InsertarInformacionValuacionUseCase;
import com.itesm.fennec.application.useCase.ListInvestmentsUseCase;
import com.itesm.fennec.application.useCase.ListarInformesUseCase;
import com.itesm.fennec.domain.model.InformeValuacion;
import com.itesm.fennec.domain.model.Investment;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

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
    @Inject
    FirebaseUserService firebaseUserService;
    @Inject
    ListarInformesUseCase listarInformesUseCase;

    @GET
    @Path("list-reportes")
    public Response obtenerReportes(@HeaderParam("Authorization") String authHeader) {
        if (authHeader == null || authHeader.isEmpty()) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build();
        }
        String token = authHeader.substring(7);
        String firebaseId;
        try {
            firebaseId = firebaseUserService.getUidFromToken(token);

            List<InformeValuacion> informeValuacion = listarInformesUseCase.execute(firebaseId);
            return Response.ok(informeValuacion).build();

        } catch(Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity("No reports found").build();
        }


    }
}