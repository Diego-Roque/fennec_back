package com.itesm.fennec.infrastructure.dto.payment;

public class CheckoutProfessionalDTO {
    private String customerEmail;
    private String successUrl;
    private String cancelUrl;

    public CheckoutProfessionalDTO() {}

    public CheckoutProfessionalDTO(String customerEmail, String successUrl, String cancelUrl) {
        this.customerEmail = customerEmail;
        this.successUrl = successUrl;
        this.cancelUrl = cancelUrl;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getCancelUrl() {
        return cancelUrl;
    }

    public void setCancelUrl(String cancelUrl) {
        this.cancelUrl = cancelUrl;
    }
}