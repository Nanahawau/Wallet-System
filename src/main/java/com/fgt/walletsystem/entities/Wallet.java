package com.fgt.walletsystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fgt.walletsystem.models.Audit;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Wallet extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="wallet_seqgen", sequenceName="Wallet_SEQ",allocationSize=1)
    private long id;
    private BigDecimal balance = BigDecimal.ZERO;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_accounts_id")
    private User user;


    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction );
        transaction.setWallet( this );
    }

    public void removeTransaction(Transaction transaction) {
        transactions.remove(transaction);
        transaction.setWallet( null );
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    @JsonIgnore
    public List<Transaction> getTransactions() {
        return transactions;
    }

    @JsonIgnore
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @JsonIgnore
    public void setUser(User user) {
        this.user = user;
    }
}
