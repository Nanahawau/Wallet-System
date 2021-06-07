package com.fgt.walletsystem.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class InitiateTransactionDTO {
    private BigDecimal amount;
    private String currency;
    private String email;
    @JsonProperty("ref")
    private String reference;
    private String transactionType;
}
