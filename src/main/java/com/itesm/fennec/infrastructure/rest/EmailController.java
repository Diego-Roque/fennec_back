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
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/api/email")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Email", description = "Operaciones de envío de correo electrónico")
public class EmailController {

    @Inject
    SendEmailUseCase sendEmailUseCase;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Enviar correo electrónico al usuario")
    @APIResponse(responseCode = "200", description = "Correo enviado exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class)))
    @APIResponse(responseCode = "500", description = "Error interno al intentar enviar el correo")
    public Response sendEmail(@RequestBody(description = "Datos del correo a enviar", required = true,
            content = @Content(schema = @Schema(implementation = SendEmailDTO.class)))SendEmailDTO sendEmailDTO) {

        try {
            sendEmailUseCase.execute(sendEmailDTO);
            return Response.ok().entity("Email sent successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to send email: " + e.getMessage()).build();
        }
    }
}
