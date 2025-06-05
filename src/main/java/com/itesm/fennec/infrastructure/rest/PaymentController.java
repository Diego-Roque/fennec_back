package com.itesm.fennec.infrastructure.rest;

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
import java.util.Map;
import java.util.HashMap;

@Path("/payments")
@Produces(MediaType.APPLICATION_JSON)
public class PaymentController {

    @ConfigProperty(name = "stripe.secret.key")
    String stripeSecretKey;

    @ConfigProperty(name = "stripe.professional.price.id")
    String professionalPriceId;

    @PostConstruct
    void init() {
        Stripe.apiKey = stripeSecretKey;
    }

    @POST
    @Path("/checkout-subscription")
    public Response checkoutProfessionalSubscription(CheckoutProfessionalDTO request) {
        try {
            System.out.println("=== STRIPE CHECKOUT DEBUG ===");
            System.out.println("Customer Email: " + request.getCustomerEmail());
            System.out.println("Success URL: " + request.getSuccessUrl());

            // Crear sesión de checkout de Stripe para suscripción profesional
            SessionCreateParams params = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.SUBSCRIPTION)
                    .setSuccessUrl(request.getSuccessUrl() + "?session_id={CHECKOUT_SESSION_ID}")
                    .setCancelUrl(request.getCancelUrl())
                    .setCustomerEmail(request.getCustomerEmail())
                    .addLineItem(
                            SessionCreateParams.LineItem.builder()
                                    .setPrice(professionalPriceId)
                                    .setQuantity(1L)
                                    .build()
                    )
                    .setSubscriptionData(
                            SessionCreateParams.SubscriptionData.builder()
                                    .putMetadata("subscription_type", "professional")
                                    .putMetadata("user_email", request.getCustomerEmail())
                                    .build()
                    )
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
    @Path("/professional-subscription-success")
    public Response professionalSubscriptionSuccess(ProfessionalSubscriptionSuccessDTO request) {
        try {
            System.out.println("=== SUBSCRIPTION SUCCESS DEBUG ===");
            System.out.println("Received sessionId: " + request.getSessionId());

            // Recuperar los detalles de la sesión de Stripe
            Session session = Session.retrieve(request.getSessionId());

            // Validar que la sesión esté completada
            if (!"complete".equals(session.getStatus())) {
                System.out.println("Payment session not completed. Status: " + session.getStatus());
                Map<String, String> error = new HashMap<>();
                error.put("error", "Payment session is not completed");
                return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
            }

            // Recuperar información de la suscripción
            String subscriptionId = session.getSubscription();
            String customerEmail = session.getCustomerEmail();

            System.out.println("Subscription activated for: " + customerEmail);
            System.out.println("Subscription ID: " + subscriptionId);

            // Aquí puedes agregar lógica adicional como:
            // - Actualizar el estado del usuario en tu base de datos
            // - Enviar email de confirmación
            // - Activar funcionalidades profesionales

            Map<String, String> response = new HashMap<>();
            response.put("message", "Professional subscription activated successfully");
            response.put("subscriptionId", subscriptionId);
            response.put("customerEmail", customerEmail);
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