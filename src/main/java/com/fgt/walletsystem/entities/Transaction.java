package com.fgt.walletsystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fgt.walletsystem.enums.TransactionType;
import com.fgt.walletsystem.models.Audit;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "transaction_logs")
public class Transaction extends Audit {

    // TODO: Add sequence generator
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="transaction_seqgen", sequenceName="Transaction_SEQ",allocationSize=1)
    private long id;
    @Column(unique = true)
    private String transactionReference;
    private TransactionType transactionType;
    private String status;
    private Date transactionDate = new Date();
    private String verifyTransactionDate;
    private BigDecimal amount;
    private String narration;
    private String currency;
    private String channel;
    private String ipAddress;
    private String fees;
    private String message;
    private boolean creditWallet = false;
    private boolean debitWallet = false;
    private String paystackReference;


    @ManyToOne( fetch = FetchType.LAZY)
    private Wallet wallet;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPaystackReference() {
        return paystackReference;
    }

    public void setPaystackReference(String paystackReference) {
        this.paystackReference = paystackReference;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
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

    public String getVerifyTransactionDate() {
        return verifyTransactionDate;
    }

    public void setVerifyTransactionDate(String verifyTransactionDate) {
        this.verifyTransactionDate = verifyTransactionDate;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public boolean isCreditWallet() {
        return creditWallet;
    }

    public void setCreditWallet(boolean creditWallet) {
        this.creditWallet = creditWallet;
    }

    public boolean isDebitWallet() {
        return debitWallet;
    }

    public void setDebitWallet(boolean debitWallet) {
        this.debitWallet = debitWallet;
    }

    @JsonIgnore
    public Wallet getWallet() {
        return wallet;
    }
    @JsonIgnore
    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
}
