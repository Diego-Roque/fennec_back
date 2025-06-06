package com.itesm.fennec.infrastructure.rest;

import com.itesm.fennec.application.service.CasaService;
import com.itesm.fennec.application.useCase.ObtenerMenorAlPromedioCasasUseCase;
import com.itesm.fennec.application.useCase.ObtenerNumCasasUseCase;
import com.itesm.fennec.application.useCase.ObtenerTodasCasasUseCase;
import com.itesm.fennec.domain.model.AlcaldiaRequest;
import com.itesm.fennec.domain.model.Casa;
import com.itesm.fennec.domain.model.CasaPrecioPromedioResult;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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
    @Inject
    ObtenerNumCasasUseCase obtenerNumCasasUseCase;
    @GET
    @Path("/num-casas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerNumCasas() {
        try {
            long numCasas = obtenerNumCasasUseCase.execute();
            return Response.ok(numCasas).build();
        }
        catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al obtener oportunidades").build();
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
    @Path("/promedio-por-alcaldia")
    public Response obtenerPromedioPorAlcaldia() {
        try {
            List<Casa> casas = obtenerTodasCasasUseCase.execute();
            Map<String, BigDecimal> promediosPorAlcaldia = casas.stream()
                    .collect(Collectors.groupingBy(
                            Casa::getAlcaldia,
                            Collectors.collectingAndThen(
                                    Collectors.averagingDouble(casa -> casa.getPrecio().doubleValue()),
                                    promedio -> BigDecimal.valueOf(promedio).setScale(2, RoundingMode.HALF_UP)
                            )
                    ));
            return Response.ok(promediosPorAlcaldia).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al obtener promedios por alcaldía: " + e.getMessage())
                    .build();
        }
    }

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
