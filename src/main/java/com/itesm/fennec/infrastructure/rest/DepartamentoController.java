package com.itesm.fennec.infrastructure.rest;

import com.itesm.fennec.application.service.DepartamentoService;
import com.itesm.fennec.application.useCase.*;
import com.itesm.fennec.domain.model.AlcaldiaRequest;
import com.itesm.fennec.domain.model.Casa;
import com.itesm.fennec.domain.model.Departamento;
import com.itesm.fennec.domain.model.DepartamentoPrecioPromedioResult;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Path("api/departamento")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Departamento", description = "Operaciones relacionadas con departamentos")
public class DepartamentoController {


    @Inject
    ObtenerPromedioPrecioDepartamentoUseCase obtenerPromedioPrecioDepartamentoUseCase;
    @POST
    @Path("/promedio")
    @Operation(summary = "Obtener precio promedio de departamentos por alcaldía")
    @APIResponse(
            responseCode = "200",
            description = "Promedio obtenido exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = DepartamentoPrecioPromedioResult.class))
    )
    @APIResponse(
            responseCode = "400",
            description = "Falta el campo de alcaldía"
    )
    @APIResponse(
            responseCode = "500",
            description = "Error interno del servidor"
    )
    public Response obtenerPromedio( @RequestBody(description = "Alcaldía de consulta", required = true,
            content = @Content(schema = @Schema(implementation = AlcaldiaRequest.class))) AlcaldiaRequest request) {
        String alcaldia = request.getAlcaldia();

        if (alcaldia == null || alcaldia.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("El campo 'alcaldia' es requerido.")
                    .build();
        }

        DepartamentoPrecioPromedioResult result = obtenerPromedioPrecioDepartamentoUseCase.execute(alcaldia);
        return Response.ok(result).build();
    }

    @Inject
    ContarDepartamentosPorAlcaldiaUseCase contarDepartamentosPorAlcaldiaUseCase;
    @POST
    @Path("/cantidad")
    @Operation(summary = "Contar departamentos en una alcaldía")
    @APIResponse(responseCode = "200", description = "Cantidad total de departamentos",
            content = @Content(schema = @Schema(implementation = Long.class)))
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response contarPorAlcaldia( @RequestBody(description = "Alcaldía", required = true,
            content = @Content(schema = @Schema(implementation = AlcaldiaRequest.class)))AlcaldiaRequest request) {
        Long cantidad = contarDepartamentosPorAlcaldiaUseCase.execute(request.getAlcaldia());
        return Response.ok(cantidad).build();
    }

    @Inject
    ObtenerPromedioM2UseCase obtenerPromedioM2UseCase;
    @POST
    @Path("/m2_promedio")
    @Operation(summary = "Obtener precio promedio por m² en una alcaldía (solo departamentos)")
    @APIResponse(responseCode = "200", description = "Promedio m²", content = @Content(schema = @Schema( format = "double")))
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPromedioM2(@RequestBody(description = "Alcaldía", required = true,
            content = @Content(schema = @Schema(implementation = AlcaldiaRequest.class))) AlcaldiaRequest request) {
        double m2 = obtenerPromedioM2UseCase.execute(
                request.getAlcaldia(),
                true
        );
        return Response.ok(m2).build();
    }

    @Inject
    PromedioTodosDepartamentosUseCase promedioTodosDepartamentosUseCase;
    @POST
    @Path("/promedio_todos")
    @Operation(summary = "Obtener precio promedio de todos los departamentos")
    @APIResponse(responseCode = "200", description = "Promedio global", content = @Content(schema = @Schema(type = SchemaType.NUMBER
            , format = "double")))
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response promedioTodosDepartamentos() {
        try {
            Double promedio = promedioTodosDepartamentosUseCase.execute();
            return Response.ok(promedio).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al obtener el promedio de precio por m²: " + e.getMessage())
                    .build();
        }
    }

    @Inject
    ObtenerTodosDepartamentosUseCase obtenerTodosDepartamentosUseCase;

    @GET
    @Path("/promedio-por-alcaldia")
    @Operation(summary = "Obtener promedios por alcaldía")
    @APIResponse(responseCode = "200", description = "Mapa de alcaldías con promedio",
            content = @Content(schema = @Schema(implementation = Map.class)))
    public Response obtenerPromedioPorAlcaldia() {
        try {
            List<Departamento> departamentos = obtenerTodosDepartamentosUseCase.execute();
            Map<String, BigDecimal> promediosPorAlcaldia = departamentos.stream()
                    .collect(Collectors.groupingBy(
                            Departamento::getAlcaldia,
                            Collectors.collectingAndThen(
                                    Collectors.averagingDouble(departamento -> departamento.getPrecio().doubleValue()),
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

    @Inject
    ObtenerPrecioM2UseCase obtenerPrecioM2UseCase;
    @POST
    @Path("/m2_todos")
    @Operation(summary = "Obtener promedio general de precio por m² de departamentos")
    @APIResponse(responseCode = "200", description = "Promedio m²",
            content = @Content(schema = @Schema(type = SchemaType.NUMBER
                    , format = "double")))
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPrecioM2() {
        try {
            Double precio = obtenerPrecioM2UseCase.execute(true);
            return Response.ok(precio).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al obtener el promedio de precio por m²: " + e.getMessage())
                    .build();
        }
    }
    @Inject
    ObtenerTodosDepartamentosUseCase obtenerDepartamentos;
    @GET
    @Path("list-departamentos")
    @Operation(summary = "Listar todos los departamentos")
    @APIResponse(responseCode = "200", description = "Lista completa de departamentos",
            content = @Content(schema = @Schema(implementation = Departamento.class)))
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTodosLosDepartamentos() {
        try {
            List<Departamento> departamentos = obtenerDepartamentos.execute();
            return Response.ok(departamentos).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al obtener la lista de casas: " + e.getMessage())
                    .build();
        }
    }
    @Inject
    ObtenerNumDepartamentosUseCase obtenerNumDepartamentosUseCase;
    @GET
    @Path("/num-departamentos")
    @Operation(summary = "Obtener número total de departamentos")
    @APIResponse(responseCode = "200", description = "Cantidad total",
            content = @Content(schema = @Schema(type = SchemaType.INTEGER
            )))
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerNumCasas() {
        try {
            long numCasas = obtenerNumDepartamentosUseCase.execute();
            return Response.ok(numCasas).build();
        }
        catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al obtener departamentos").build();
        }
    }



    @Inject
    ObtenerMenorAlPromedioDepartamentoUseCase obtenerMenorAlPromedioDepartamentoUseCase;

    @GET
    @Path("/oportunidades")
    @Operation(summary = "Listar departamentos con precio por debajo del promedio")
    @APIResponse(responseCode = "200", description = "Lista de oportunidades",
            content = @Content(schema = @Schema(implementation = Departamento.class)))
    @APIResponse(responseCode = "404", description = "No se encontraron oportunidades")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerMenorAlPromedio() {
        try {
            List<Departamento> oportunidades = obtenerMenorAlPromedioDepartamentoUseCase.execute();
            if (oportunidades == null || oportunidades.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("No se encontraron departamentos por debajo del promedio").build();
            }
            return Response.ok(oportunidades).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al obtener oportunidades").build();
        }
    }
}