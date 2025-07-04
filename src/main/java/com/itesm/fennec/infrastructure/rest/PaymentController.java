package com.itesm.fennec.infrastructure.rest;

import com.itesm.fennec.application.useCase.ActualizarSuscripcionUseCase;
import com.itesm.fennec.domain.model.User;
import com.itesm.fennec.infrastructure.dto.payment.CheckoutProfessionalDTO;
import com.itesm.fennec.infrastructure.dto.payment.ProfessionalSubscriptionSuccessDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.inject.Inject;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import jakarta.annotation.PostConstruct;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.Map;
import java.util.HashMap;
import java.util.Objects;

@Path("/payments")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Pagos", description = "Operaciones relacionadas con suscripciones y pagos con Stripe")
public class PaymentController {

    @ConfigProperty(name = "stripe.secret.key")
    String stripeSecretKey;

    @PostConstruct
    void init() {
        Stripe.apiKey = stripeSecretKey;
    }

    @Inject
    ActualizarSuscripcionUseCase actualizarSuscripcionUseCase;

    @POST
    @Path("/checkout-subscription")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Crear sesión de checkout de Stripe para suscripción profesional")
    @APIResponse(
            responseCode = "200",
            description = "Sesión de pago creada correctamente",
            content = @Content(mediaType = "application/json")
    )
    @APIResponse(
            responseCode = "400",
            description = "Error al crear la sesión de pago"
    )
    @APIResponse(
            responseCode = "500",
            description = "Error interno del servidor"
    )
    public Response checkoutProfessionalSubscription( @RequestBody(
                                                                  description = "Información del usuario para crear sesión de pago",
                                                                  required = true,
                                                                  content = @Content(schema = @Schema(implementation = CheckoutProfessionalDTO.class))
                                                          )
                                                          CheckoutProfessionalDTO request
    )  {
        String finalprice= "";
        String price1="price_1RXtKjBh8F8W0UaWpX7hgiBq";
        String price2="price_1RXtLjBh8F8W0UaWkPdkDTkB";
        String price3="price_1RXtM1Bh8F8W0UaWumrmFJX6";

        if(Objects.equals(request.getType(), "starter")){
            finalprice=price1;
        }
        if(Objects.equals(request.getType(), "professional")){
            finalprice=price2;
        }
        if(Objects.equals(request.getType(), "empresarial")){
            finalprice=price3;
        }
        String professionalPriceId;
        try {
            System.out.println("=== STRIPE CHECKOUT DEBUG ===");
            System.out.println("Customer Email: " + request.getCustomerEmail());


            // Crear sesión de checkout de Stripe para suscripción profesional
            SessionCreateParams params = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.SUBSCRIPTION)
                    .setSuccessUrl("http://localhost:3000/success" + "?session_id={CHECKOUT_SESSION_ID}")
                    .setCancelUrl("http://localhost:3000/signup")
                    .setCustomerEmail(request.getCustomerEmail())
                    .addLineItem(
                            SessionCreateParams.LineItem.builder()
                                    .setPrice(finalprice)
                                    .setQuantity(1L)
                                    .build()
                    )
                    .setSubscriptionData(
                            SessionCreateParams.SubscriptionData.builder()
                                    .putMetadata("subscription_type", "professional")
                                    .putMetadata("user_email", request.getCustomerEmail())

                                    .build()
                    )
                    .putMetadata("firebase_uid", request.getUid())
                    .putMetadata("subscription_type", request.getType())
                    .build();

            Session session = Session.create(params);

            System.out.println("Stripe session created successfully: " + session.getId());

            Map<String, String> response = new HashMap<>();
            response.put("sessionId", session.getId());
            response.put("checkoutUrl", session.getUrl());
            response.put("status", "created");

            return Response.ok().entity(response).build();

        } catch (StripeException e) {
            System.out.println("STRIPE ERROR: " + e.getMessage());
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error creating professional subscription checkout: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
        } catch (Exception e) {
            System.out.println("PAYMENT ERROR: " + e.getMessage());
            Map<String, String> error = new HashMap<>();
            error.put("error", "Internal server error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
        }
    }

    @POST
    @Path("/payment-success")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Confirmar éxito de suscripción y actualizar el rol del usuario")
    @APIResponse(
            responseCode = "200",
            description = "Suscripción activada y usuario actualizado",
            content = @Content(mediaType = "application/json")
    )
    @APIResponse(
            responseCode = "400",
            description = "La sesión de pago no está completa o es inválida"
    )
    @APIResponse(
            responseCode = "500",
            description = "Error interno del servidor"
    )
    public Response professionalSubscriptionSuccess(@RequestBody(
                                                                description = "Información de la sesión de pago",
                                                                required = true,
                                                                content = @Content(schema = @Schema(implementation = ProfessionalSubscriptionSuccessDTO.class))
                                                        )
                                                        ProfessionalSubscriptionSuccessDTO request
    ) {
        try {
            System.out.println("=== SUBSCRIPTION SUCCESS DEBUG ===");
            System.out.println("Received sessionId: " + request.getSessionId());

            Session session = Session.retrieve(request.getSessionId());

            if (!"complete".equals(session.getStatus())) {
                System.out.println("Payment session not completed. Status: " + session.getStatus());
                Map<String, String> error = new HashMap<>();
                error.put("error", "Payment session is not completed");
                return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
            }

            String subscriptionId = session.getSubscription();
            String customerEmail = session.getCustomerEmail();
            String uid = session.getMetadata().get("firebase_uid");
            String subscriptionType = session.getMetadata().get("subscription_type");

            System.out.println("Subscription activated for: " + customerEmail);
            System.out.println("Subscription ID: " + subscriptionId);
            System.out.println("User UID: " + uid);

            User updatedUser = actualizarSuscripcionUseCase.execute(uid, subscriptionType);

            System.out.println("User role updated: " + updatedUser.getTipoRole());

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Professional subscription activated successfully");
            response.put("subscriptionId", subscriptionId);
            response.put("customerEmail", customerEmail);
            response.put("user", updatedUser);
            response.put("status", "active");

            return Response.ok().entity(response).build();

        } catch (StripeException e) {
            System.out.println("STRIPE ERROR: " + e.getMessage());
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error processing subscription success: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
        } catch (Exception e) {
            System.out.println("SUBSCRIPTION SUCCESS ERROR: " + e.getMessage());
            Map<String, String> error = new HashMap<>();
            error.put("error", "Internal server error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
        }
    }

}