package com.fgt.walletsystem.domains.paystack;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CreateCustomerResponse {

    @JsonProperty("status")
    private boolean status;
    @JsonProperty("message")
    private String message;
    @JsonProperty("data")
    private CustomerData customerData;

    @Data
    public class CustomerData {
        private long id;
        private List<?> transactions;
        private List<?> subscriptions;
        private List<?> authorizations;
        @JsonProperty("first_name")
        private String firstName;
        @JsonProperty("last_name")
        private String lastName;
        @JsonProperty("risk_action")
        private String riskAction;
        @JsonProperty("phone")
        private String phoneNumber;
        private String email;
        private long integration;
        private String domain;
        @JsonProperty("customer_code")
        private String customerCode;
        private String identified;
        private String identifications;
        private String createdAt;
        private String updatedAt;
        private Object metadata;
    }
}


