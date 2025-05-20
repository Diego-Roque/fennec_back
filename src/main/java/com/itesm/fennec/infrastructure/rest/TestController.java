package com.itesm.fennec.infrastructure.rest;

import com.itesm.fennec.infrastructure.dto.dashboard.PropertyDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/propiedades")
public class TestController {

    @GET
    @Path("/property")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPropertyData() {
        List<PropertyDTO> properties = List.of(
                new PropertyDTO(
                        "Casa de Playa",
                        "Tulum, México",
                        "Hermosa casa frente al mar con acceso privado a la playa.",
                        "Casa",
                        450000,
                        320,
                        3,
                        4,
                        List.of(400000, 250000, 440000),
                        500000,
                        550000,
                        5.2,
                        10.0,
                        7,
                        85,
                        List.of("Huracan", "Regulación de alquileres"),
                        "Low",
                        List.of("Piscina", "Wi-Fi", "Aire acondicionado"),
                        "A",
                        "+52 998 123 4567"
                ),
                new PropertyDTO(
                        "Departamento en Roma",
                        "Roma Norte, CDMX",
                        "Moderno departamento cerca de restaurantes y parques.",
                        "Departamento",
                        4900000,
                        110,
                        2,
                        3,
                        List.of(4700000, 4600000, 4500000),
                        5200000,
                        5700000,
                        3.8,
                        12.5,
                        6,
                        90,
                        List.of("Zonificación", "Robo"),
                        "Medium",
                        List.of("Gimnasio", "Seguridad 24h"),
                        "B",
                        "+52 55 1234 5678"
                ),
                new PropertyDTO(
                        "Departamento en Roma parte 2",
                        "Roma Norte, CDMX",
                        "Moderno departamento cerca de restaurantes y parques.",
                        "Departamento",
                        4900000,
                        110,
                        2,
                        3,
                        List.of(4700000, 4600000, 4500000),
                        5200000,
                        5700000,
                        3.8,
                        12.5,
                        6,
                        90,
                        List.of("Zonificación", "Robo"),
                        "Medium",
                        List.of("Gimnasio", "Seguridad 24h"),
                        "B",
                        "+52 55 1234 5678"
                )
        );

        return Response.ok(properties).build();
    }
}
