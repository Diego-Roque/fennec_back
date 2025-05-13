package com.itesm.fennec.infrastructure.rest;

import com.itesm.fennec.application.useCase.SendEmailUseCase;
import com.itesm.fennec.infrastructure.dto.SendEmailDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/email")
public class EmailController {

    @Inject
    SendEmailUseCase sendEmailUseCase;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendEmail(SendEmailDTO sendEmailDTO) {
        try {
            sendEmailUseCase.execute(sendEmailDTO);
            return Response.ok().entity("Email sent successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to send email: " + e.getMessage()).build();
        }
    }
}
