package com.itesm.fennec.infrastructure.rest;


import com.itesm.fennec.application.useCase.*;
import com.itesm.fennec.domain.model.AlcaldiaRequest;
import com.itesm.fennec.domain.model.Casa;
import com.itesm.fennec.domain.model.CasaPrecioPromedioResult;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Path("api/casa")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Casas", description = "Operaciones relacionadas con propiedades tipo casa")
public class CasaController {



    @Inject
    ObtenerPromedioPrecioCasaUseCase obtenerPromedioPrecioCasaUseCase;
    @POST
    @Path("/promedio")
    @Operation(summary = "Obtener promedio de precio por alcaldía",
            description = "Devuelve el promedio de precios de casas para una alcaldía específica")
    @APIResponse(responseCode = "200", description = "Promedio devuelto correctamente")
    @APIResponse(responseCode = "400", description = "Campo 'alcaldia' es requerido")
    public Response obtenerPromedio(AlcaldiaRequest request) {
        String alcaldia = request.getAlcaldia();

        if (alcaldia == null || alcaldia.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("El campo 'alcaldia' es requerido.")
                    .build();
        }

        CasaPrecioPromedioResult result = obtenerPromedioPrecioCasaUseCase.execute(alcaldia);
        return Response.ok(result).build();
    }

    @Inject
    ContarCasasPorAlcaldiaUseCase contarCasasPorAlcaldiaUseCase;
    @POST
    @Path("/cantidad")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Contar casas por alcaldía",
            description = "Devuelve el número total de casas en una alcaldía específica")
    @APIResponse(responseCode = "200", description = "Cantidad devuelta correctamente")
    public Response contarPorAlcaldia(AlcaldiaRequest request) {
        Long cantidad = contarCasasPorAlcaldiaUseCase.execute(request.getAlcaldia());
        return Response.ok(cantidad).build();
    }

    @Inject
    ObtenerPromedioM2UseCase obtenerPromedioM2UseCase;
    @POST
    @Path("/m2_promedio")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Obtener promedio de precio por m²",
            description = "Devuelve el promedio de precio por metro cuadrado en una alcaldía, para casas o departamentos")
    @APIResponse(responseCode = "200", description = "Promedio de m² calculado correctamente")
    public Response obtenerPromedioM2(AlcaldiaRequest request) {
        double m2 = obtenerPromedioM2UseCase.execute(
                request.getAlcaldia(),
                false
        );
        return Response.ok(m2).build();
    }


    @Inject
    PromedioTodasCasasUseCase promedioTodasCasasUseCase;
    @POST
    @Path("/promedio_todas")
    @APIResponse(responseCode = "200", description = "Promedio total calculado")
    @APIResponse(responseCode = "500", description = "Error al obtener el promedio")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response promedioTodasCasas() {
        try {
            Double promedio = promedioTodasCasasUseCase.execute();
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
    @Operation(summary = "Número total de casas",
            description = "Devuelve la cantidad total de casas en el sistema")
    @APIResponse(responseCode = "200", description = "Cantidad obtenida correctamente")
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

    @Inject
    ObtenerPrecioM2UseCase obtenerPrecioM2UseCase;
    @POST
    @Path("/m2_todas")
    @Operation(summary = "Obtener promedio general de m²",
            description = "Calcula el promedio de m² general para casas o departamentos")
    @APIResponse(responseCode = "200", description = "Promedio general de m² obtenido")
    @APIResponse(responseCode = "500", description = "Error interno")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPrecioM2() {
        try {
            Double precio = obtenerPrecioM2UseCase.execute(false);
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
    @Operation(summary = "Promedio de precios por alcaldía",
            description = "Calcula el promedio de precios agrupado por alcaldía")
    @APIResponse(responseCode = "200", description = "Promedios agrupados por alcaldía obtenidos")
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
    @Produces(MediaType.APPLICATION_JSON)@Operation(summary = "Lista de todas las casas",
            description = "Devuelve la lista completa de casas registradas")
    @APIResponse(responseCode = "200", description = "Lista obtenida correctamente")
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
    @Operation(summary = "Casas por debajo del promedio",
            description = "Devuelve las casas cuyo precio está por debajo del promedio general")
    @APIResponse(responseCode = "200", description = "Lista de casas obtenida")
    @APIResponse(responseCode = "404", description = "No se encontraron casas por debajo del promedio")
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
