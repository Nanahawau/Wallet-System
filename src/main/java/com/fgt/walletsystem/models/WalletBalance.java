package com.fgt.walletsystem.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WalletBalance {
    private long id;
    private BigDecimal balance;
}
