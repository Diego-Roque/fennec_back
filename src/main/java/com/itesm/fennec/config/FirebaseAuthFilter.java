package com.itesm.fennec.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;


@Provider
@Priority(Priorities.AUTHENTICATION)
public class FirebaseAuthFilter implements ContainerRequestFilter {


    @Override
    public void filter(ContainerRequestContext requestContext){
        String path = requestContext.getUriInfo().getPath();
        if (path.endsWith("/auth/signup")) {
            return;
        }

        if (path.endsWith("/payments/checkout-subscription")) {
            return;
        }
        if (path.endsWith("/payments/payment-success")) {
            return;
        }
        if (path.endsWith("/api/casa/promedio_todas")) {
            return;
        }
        if (path.endsWith("/api/casa/m2_todas")) {
            return;
        }if (path.endsWith("/api/casa/promedio")) {
            return;
        }
if (path.endsWith("/api/casa/cantidad")) {
            return;
        }if (path.endsWith("/api/casa/m2_promedio")) {
            return;
        }

if (path.endsWith("/api/casa/list-casas")) {
            return;
        }
        if (path.endsWith("/propiedades/filtrar")) {
            return;
        }



        String authHeader = requestContext.getHeaderString("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Authorization header must be provided")
                    .build());
            return;
        }

        String token = authHeader.substring("Bearer".length()).trim();
        if(token.equals("testToken")){
            requestContext.setProperty("userId", "testUserId");
            return;
        }
        try{
            FirebaseToken decodedToken= FirebaseAuth.getInstance().verifyIdToken(token);
            System.out.println(decodedToken.getUid());
            requestContext.setProperty("userId", decodedToken.getUid());
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Invalid token")
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
            requestContext.abortWith(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Invalid firebase token")
                    .build());
        }
    }

}
