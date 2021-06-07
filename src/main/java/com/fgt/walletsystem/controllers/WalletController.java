package com.fgt.walletsystem.controllers;

import com.fgt.walletsystem.dtos.CreateWalletDTO;
import com.fgt.walletsystem.dtos.InitiateTransactionDTO;
import com.fgt.walletsystem.models.Response;
import com.fgt.walletsystem.services.CustomerInterface;
import com.fgt.walletsystem.services.PaymentInterface;
import com.fgt.walletsystem.services.TransactionsInterface;
import com.fgt.walletsystem.services.WalletInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/wallet")
@Validated
public class WalletController {

    private final PaymentInterface paymentInterface;
    private final CustomerInterface customerInterface;
    private final WalletInterface walletInterface;
    private final TransactionsInterface transactionsInterface;

    public WalletController(PaymentInterface paymentInterface, CustomerInterface customerInterface, WalletInterface walletInterface, TransactionsInterface transactionsInterface) {
        this.paymentInterface = paymentInterface;
        this.customerInterface = customerInterface;
        this.walletInterface = walletInterface;
        this.transactionsInterface = transactionsInterface;
    }

    @PostMapping
    public ResponseEntity<Response> create(@RequestBody @Valid CreateWalletDTO createWalletDTO)  {
        return ResponseEntity.ok(customerInterface.createCustomer(createWalletDTO));
    }

    @GetMapping("/balance/{id}")
    public ResponseEntity<Response> balance(@PathVariable @Valid long id)  {
        return ResponseEntity.ok(walletInterface.getBalance(id));
    }

    @PostMapping("/fund")
    public ResponseEntity<Response> fundWallet (@RequestBody @Valid InitiateTransactionDTO initiateTransactionDTO)  {
        return ResponseEntity.ok(paymentInterface.initializeTransaction(initiateTransactionDTO));
    }

    @PostMapping("/paystack-callback")
    public ResponseEntity<Response> paystackCallback (@RequestParam("trxref") @Valid String reference) {
        return ResponseEntity.ok(paymentInterface.verifyPayment(reference));
    }





}
