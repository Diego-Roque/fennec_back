package com.itesm.fennec.infrastructure.rest;

import com.itesm.fennec.application.service.PropertyService;
import com.itesm.fennec.domain.model.Property;
import com.itesm.fennec.domain.model.PaginatedResult;
import com.itesm.fennec.infrastructure.dto.PropertyResponseDTO;
import com.itesm.fennec.infrastructure.dto.PaginatedResponseDTO;
import com.itesm.fennec.infrastructure.persistence.mapper.PropertyMapper;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.DefaultValue;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("/api")
@RequestScoped
public class PropertyResource {

    private final PropertyService propertyService;
    private final PropertyMapper mapper;

    @Inject
    public PropertyResource(PropertyService propertyService, PropertyMapper mapper) {
        this.propertyService = propertyService;
        this.mapper = mapper;
    }

    @GET
    @Path("/get-all-properties")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProperties(
            @QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("limit") @DefaultValue("10") int limit) {

        if (page < 1 || limit < 1) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Page and limit must be positive integers.")
                    .build();
        }

        try {
            PaginatedResult<Property> result = propertyService.getAllProperties(page, limit);

            List<PropertyResponseDTO> dtoList = result.getData().stream()
                    .map(mapper::toDTO)
                    .collect(Collectors.toList());

            PaginatedResponseDTO<PropertyResponseDTO> response = new PaginatedResponseDTO<>(
                    dtoList,
                    result.getTotal(),
                    result.getPage(),
                    result.getLimit(),
                    result.getTotalPages()
            );

            return Response.ok(response).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error retrieving properties: " + e.getMessage())
                    .build();
        }
    }
}
