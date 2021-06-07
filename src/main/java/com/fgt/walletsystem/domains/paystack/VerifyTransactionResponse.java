package com.fgt.walletsystem.domains.paystack;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class VerifyTransactionResponse {
    private boolean status;
    private String message;
    @JsonProperty("data")
    private VerifyTransactionData transactionData;


    @Data
    public class VerifyTransactionData {
        private long id;
        private String domain;
        private String status;
        private String reference;
        private BigDecimal amount;
        private String message;
        @JsonProperty("gateway_response")
        private String gatewayResponse;
        @JsonProperty("paid_at")
        private String paidAt;
        @JsonProperty("created_at")
        private String createdAt;
        private String channel;
        @JsonProperty("ip_address")
        private String currency;
        private String ipAddress;
        private String metadata;
        private Log log;
        private String fees;
        @JsonProperty("fees_split")
        private Object feeSplits;
        private Authorization authorization;
        private Customer customer;
        private String plan;
        private Object split;
        @JsonProperty("order_id")
        private String orderId;
        @JsonProperty("paidAt")
        private String paidAtSecond;
        @JsonProperty("createdAt")
        private String createdAtSecond;
        @JsonProperty("requested_amount")
        private BigDecimal requestedAmount;
        @JsonProperty("pos_transaction_data")
        private Object posTransactionData;
        private String source;
        @JsonProperty("transaction_date")
        private String transactionDate;
        @JsonProperty("plan_object")
        private Object planObject;
        @JsonProperty("subaccount")
        private Object subAccount;
    }

    @Data
    public class Log {
        @JsonProperty("time_spent")
        private int timeSpent;
        @JsonProperty("start_time")
        private int startTime;
        private int attempts;
        private String authentication;
        private int errors;
        private boolean success;
        private boolean mobile;
        private List<?> input;
        @JsonProperty("history")
        private List<History> histories;


    }

    @Data
    public class History {
        private String type;
        private String message;
        private int time;
    }

    @Data
    public class Authorization {
        @JsonProperty("authorization_code")
        private String authorizationCode;
        @JsonProperty("card_type")
        private String cardType;
        @JsonProperty("last4")
        private String lastFourDigitsOfCards;
        @JsonProperty("exp_month")
        private String expiryMonth;
        @JsonProperty("exp_year")
        private String expiryYear;
        private String bin;
        private String bank;
        private String channel;
        private String signature;
        private boolean reusable;
        @JsonProperty("country_code")
        private String countryCode;
        @JsonProperty("account_name")
        private String accountName;
        private String brand;
    }

    @Data
    public class Customer {
        private long id;
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
        @JsonProperty("international_format_phone")
        private String intlPhoneNumberFormat;
        @JsonProperty("customer_code")
        private String customerCode;
    }
}
