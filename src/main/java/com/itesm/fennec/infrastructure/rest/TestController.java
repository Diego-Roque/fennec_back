package com.itesm.fennec.infrastructure.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Map;

@Path("/api/propiedades")
public class TestController {

    @GET
    @Path("/property")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPropertyData() {
        Map<String, Object> propertyData = Map.ofEntries(
        Map.entry("name", "Casa de Playa"),
        Map.entry("location", "Tulum, México"),
        Map.entry("description", "Hermosa casa frente al mar con acceso privado a la playa."),
        Map.entry("price", 450000),
        Map.entry("size", 320),
        Map.entry("bathrooms", 3),
        Map.entry("bedrooms", 4),
        Map.entry("previousPrices", List.of(400000, 420000, 440000)),
        Map.entry("valuation3Years", 500000),
        Map.entry("valuation5Years", 550000),
        Map.entry("growthRate", 5.2),
        Map.entry("roiMonthly", 1800),
        Map.entry("breakevenYears", 7),
        Map.entry("occupancyRate", 85),
        Map.entry("riskFactors", List.of("Huracanes", "Regulación de alquileres")),
        Map.entry("amenities", List.of("Piscina", "Wi-Fi", "Aire acondicionado")),
        Map.entry("investmentGrade", "A"),
        Map.entry("phone", "+52 998 123 4567")
);

        return Response.ok(propertyData).build();
    }
}