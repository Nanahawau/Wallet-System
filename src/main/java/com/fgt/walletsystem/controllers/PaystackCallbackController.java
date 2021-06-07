package com.fgt.walletsystem.controllers;

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
@RequestMapping("/api/v1/paystack")
@Validated
public class PaystackCallbackController {


    private final PaymentInterface paymentInterface;


    public PaystackCallbackController(PaymentInterface paymentInterface) {
        this.paymentInterface = paymentInterface;
    }

    @GetMapping("/callback")
    public ResponseEntity<Response> paystackCallback (@RequestParam("trxref") @Valid String reference) {
        return ResponseEntity.ok(paymentInterface.verifyPayment(reference));
    }
}
