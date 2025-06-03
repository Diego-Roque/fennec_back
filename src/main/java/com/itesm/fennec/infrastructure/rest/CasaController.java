package com.itesm.fennec.infrastructure.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itesm.fennec.application.service.CasaService;
import com.itesm.fennec.application.useCase.ObtenerMenorAlPromedioCasasUseCase;
import com.itesm.fennec.application.useCase.ObtenerTodasCasasUseCase;
import com.itesm.fennec.domain.model.AlcaldiaRequest;
import com.itesm.fennec.domain.model.Casa;
import com.itesm.fennec.domain.model.CasaPrecioPromedioResult;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;


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

    @POST
    @Path("/m2_promedio")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPromedioM2(AlcaldiaRequest request) {
        double m2 = service.obtenerPromedioM2(request.getAlcaldia());
        return Response.ok(m2).build();
    }

    @POST
    @Path("/promedio_todas")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response promedioTodasCasas() {
        try {
            Double promedio = service.PromedioTodasCasas();
            return Response.ok(promedio).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al obtener el promedio de precio por m²: " + e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/m2_todas")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPrecioM2() {
        try {
            Double precio = service.obtenerPrecioM2UseCase();
            return Response.ok(precio).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al obtener el promedio de precio por m²: " + e.getMessage())
                    .build();
        }
    }

    @Inject
    ObtenerTodasCasasUseCase obtenerTodasCasasUseCase;
    @GET
    @Path("list-casas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTodasLasCasas() {
        try {
            List<Casa> casas = obtenerTodasCasasUseCase.execute();
            return Response.ok(casas).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al obtener la lista de casas: " + e.getMessage())
                    .build();
        }
    }

    @Inject
    ObtenerMenorAlPromedioCasasUseCase obtenerMenorAlPromedioCasasUseCase;

    @GET
    @Path("/oportunidades")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerMenorAlPromedio() {
        try {
            List<Casa> oportunidades = obtenerMenorAlPromedioCasasUseCase.execute();
            if (oportunidades == null || oportunidades.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("No se encontraron casas por debajo del promedio").build();
            }
            return Response.ok(oportunidades).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al obtener oportunidades").build();
        }
    }


}
