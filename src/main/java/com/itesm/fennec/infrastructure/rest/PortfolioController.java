package com.itesm.fennec.infrastructure.rest;

import com.itesm.fennec.application.useCase.Portfolio.GetUserPortfolioUseCase;
import jakarta.ws.rs.Path;
import com.itesm.fennec.domain.model.User;
import com.itesm.fennec.infrastructure.dto.PortfolioResponse;
import com.itesm.fennec.infrastructure.persistence.mapper.InvestmentResponseMapper;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;

@Path("/portfolio")
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class PortfolioController {

    private final GetUserPortfolioUseCase getUserPortfolioUseCase;

    @GET
    @Path("/{userId}")
    public Response getPortfolio(@PathParam("userId") Integer userId) {
        User user = new User();
        user.setId(userId);

        var portfolio = getUserPortfolioUseCase.execute(user);

        var response = new PortfolioResponse();
        response.setUserId(userId);
        response.setTotalInvested(portfolio.getTotalInvested());
        response.setPropertyCount(portfolio.getPropertyCount());
        response.setInvestments(
                portfolio.getInvestments().stream()
                        .map(InvestmentResponseMapper::toResponse)
                        .collect(Collectors.toList())
        );

        return Response.ok(response).build();
    }
}