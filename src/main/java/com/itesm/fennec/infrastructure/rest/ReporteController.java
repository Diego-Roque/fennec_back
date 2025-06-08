package com.itesm.fennec.infrastructure.rest;

import com.itesm.fennec.application.useCase.GetUidFromTokenUseCase;
import com.itesm.fennec.application.useCase.InsertarInformacionValuacionUseCase;
import com.itesm.fennec.application.useCase.ListarInformesUseCase;
import com.itesm.fennec.domain.model.InformeValuacion;
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

import java.util.List;

@Path("/api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Reportes", description = "Operaciones relacionadas con la creación y consulta de reportes de valuación")
public class ReporteController {

    @Inject
    InsertarInformacionValuacionUseCase insertarInformacionValuacionUseCase;

    @POST
    @Path("/create-new-report")
    @Operation(
            summary = "Generar nuevo reporte de valuación",
            description = "Crea un nuevo informe de valuación a partir de los datos proporcionados"
    )
    @APIResponse(
            responseCode = "200",
            description = "Reporte generado exitosamente"
    )
    @APIResponse(
            responseCode = "500",
            description = "Error interno al generar el reporte"
    )
    public Response generarReporte(@RequestBody(
                                               description = "Datos del informe de valuación",
                                               required = true,
                                               content = @Content(schema = @Schema(implementation = InformeValuacion.class))
                                       )
                                       InformeValuacion informe
    ) {

        try {
            insertarInformacionValuacionUseCase.execute(informe);
            return Response.ok().entity("{\"mensaje\": \"Reporte generado exitosamente\"}").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error al generar el reporte\"}").build();
        }
    }
    @Inject
    GetUidFromTokenUseCase getUidFromTokenUseCase;
    @Inject
    ListarInformesUseCase listarInformesUseCase;

    @GET
    @Path("list-reportes")
    @Operation(
            summary = "Listar reportes del usuario autenticado",
            description = "Devuelve todos los informes de valuación generados por el usuario autenticado"
    )
    @APIResponse(
            responseCode = "200",
            description = "Lista de reportes obtenida exitosamente",
            content = @Content(schema = @Schema(implementation = InformeValuacion.class))
    )
    @APIResponse(
            responseCode = "401",
            description = "Token de autorización no proporcionado o inválido"
    )
    @APIResponse(
            responseCode = "400",
            description = "Error al recuperar los reportes"
    )
    public Response obtenerReportes(@HeaderParam("Authorization") String authHeader) {
        if (authHeader == null || authHeader.isEmpty()) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build();
        }
        String token = authHeader.substring(7);
        String firebaseId;
        try {
            firebaseId = getUidFromTokenUseCase.execute(token);

            List<InformeValuacion> informeValuacion = listarInformesUseCase.execute(firebaseId);
            return Response.ok(informeValuacion).build();

        } catch(Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity("No reports found").build();
        }


    }
}