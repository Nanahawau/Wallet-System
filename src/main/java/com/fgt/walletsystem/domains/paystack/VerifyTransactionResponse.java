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
        @JsonProperty("transaction_date")
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


        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getReference() {
            return reference;
        }

        public void setReference(String reference) {
            this.reference = reference;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getGatewayResponse() {
            return gatewayResponse;
        }

        public void setGatewayResponse(String gatewayResponse) {
            this.gatewayResponse = gatewayResponse;
        }

        public String getPaidAt() {
            return paidAt;
        }

        public void setPaidAt(String paidAt) {
            this.paidAt = paidAt;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getIpAddress() {
            return ipAddress;
        }

        public void setIpAddress(String ipAddress) {
            this.ipAddress = ipAddress;
        }

        public String getMetadata() {
            return metadata;
        }

        public void setMetadata(String metadata) {
            this.metadata = metadata;
        }

        public Log getLog() {
            return log;
        }

        public void setLog(Log log) {
            this.log = log;
        }

        public String getFees() {
            return fees;
        }

        public void setFees(String fees) {
            this.fees = fees;
        }

        public Object getFeeSplits() {
            return feeSplits;
        }

        public void setFeeSplits(Object feeSplits) {
            this.feeSplits = feeSplits;
        }

        public Authorization getAuthorization() {
            return authorization;
        }

        public void setAuthorization(Authorization authorization) {
            this.authorization = authorization;
        }

        public Customer getCustomer() {
            return customer;
        }

        public void setCustomer(Customer customer) {
            this.customer = customer;
        }

        public String getPlan() {
            return plan;
        }

        public void setPlan(String plan) {
            this.plan = plan;
        }

        public Object getSplit() {
            return split;
        }

        public void setSplit(Object split) {
            this.split = split;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getPaidAtSecond() {
            return paidAtSecond;
        }

        public void setPaidAtSecond(String paidAtSecond) {
            this.paidAtSecond = paidAtSecond;
        }

        public String getCreatedAtSecond() {
            return createdAtSecond;
        }

        public void setCreatedAtSecond(String createdAtSecond) {
            this.createdAtSecond = createdAtSecond;
        }

        public BigDecimal getRequestedAmount() {
            return requestedAmount;
        }

        public void setRequestedAmount(BigDecimal requestedAmount) {
            this.requestedAmount = requestedAmount;
        }

        public Object getPosTransactionData() {
            return posTransactionData;
        }

        public void setPosTransactionData(Object posTransactionData) {
            this.posTransactionData = posTransactionData;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getTransactionDate() {
            return transactionDate;
        }

        public void setTransactionDate(String transactionDate) {
            this.transactionDate = transactionDate;
        }

        public Object getPlanObject() {
            return planObject;
        }

        public void setPlanObject(Object planObject) {
            this.planObject = planObject;
        }

        public Object getSubAccount() {
            return subAccount;
        }

        public void setSubAccount(Object subAccount) {
            this.subAccount = subAccount;
        }

        @Override
        public String toString() {
            return "VerifyTransactionData{" +
                    "id=" + id +
                    ", domain='" + domain + '\'' +
                    ", status='" + status + '\'' +
                    ", reference='" + reference + '\'' +
                    ", amount=" + amount +
                    ", message='" + message + '\'' +
                    ", gatewayResponse='" + gatewayResponse + '\'' +
                    ", paidAt='" + paidAt + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", channel='" + channel + '\'' +
                    ", currency='" + currency + '\'' +
                    ", ipAddress='" + ipAddress + '\'' +
                    ", metadata='" + metadata + '\'' +
                    ", log=" + log +
                    ", fees='" + fees + '\'' +
                    ", feeSplits=" + feeSplits +
                    ", authorization=" + authorization +
                    ", customer=" + customer +
                    ", plan='" + plan + '\'' +
                    ", split=" + split +
                    ", orderId='" + orderId + '\'' +
                    ", paidAtSecond='" + paidAtSecond + '\'' +
                    ", createdAtSecond='" + createdAtSecond + '\'' +
                    ", requestedAmount=" + requestedAmount +
                    ", posTransactionData=" + posTransactionData +
                    ", source='" + source + '\'' +
                    ", transactionDate='" + transactionDate + '\'' +
                    ", planObject=" + planObject +
                    ", subAccount=" + subAccount +
                    '}';
        }
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
