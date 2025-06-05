package com.itesm.fennec.infrastructure.dto.payment;

public class ProfessionalSubscriptionSuccessDTO {
    private String sessionId;

    public ProfessionalSubscriptionSuccessDTO() {}

    public ProfessionalSubscriptionSuccessDTO(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}