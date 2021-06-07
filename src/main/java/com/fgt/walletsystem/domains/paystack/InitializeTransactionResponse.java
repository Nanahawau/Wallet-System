package com.fgt.walletsystem.domains.paystack;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InitializeTransactionResponse {
    @JsonProperty("status")
    private boolean status;
    @JsonProperty("message")
    private String message;
    @JsonProperty("data")
    private InitializeTransactionData initializeTransactionData;


    @Data
    public class InitializeTransactionData {
        @JsonProperty("authorization_url")
        private String authorizationUrl;
        @JsonProperty("access_code")
        private String accessCode;
        @JsonProperty("reference")
        private String transactionReference;
    }
}



