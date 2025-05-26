package com.itesm.fennec.infrastructure.rest;



import com.itesm.fennec.application.useCase.Investment.CreateInvestmentUseCase;
import com.itesm.fennec.application.useCase.Investment.RemoveInvestmentUseCase;
import com.itesm.fennec.domain.model.User;
import com.itesm.fennec.domain.model.Property;
import com.itesm.fennec.infrastructure.dto.CreateInvestmentRequest;
import com.itesm.fennec.infrastructure.dto.InvestmentResponse;
import com.itesm.fennec.infrastructure.persistence.mapper.InvestmentResponseMapper;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@Path("/api/investments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class InvestmentController {

    private final CreateInvestmentUseCase createInvestmentUseCase;
    private final RemoveInvestmentUseCase removeInvestmentUseCase;

    @POST
    public Response createInvestment(CreateInvestmentRequest request) {
        // Crear stubs de dominio (solo con IDs) según Clean Architecture
        User user = new User();
        user.setId(request.getUserId());

        Property property = new Property();
        property.setId(request.getPropertyId());

        var investment = createInvestmentUseCase.execute(
                user,
                property,
                request.getAmountInvested(),
                request.getParticipationPercentage()
        );

        InvestmentResponse response = InvestmentResponseMapper.toResponse(investment);
        return Response.ok(response).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteInvestment(@PathParam("id") Long id) {
        removeInvestmentUseCase.execute(id);
        return Response.noContent().build();
    }
}