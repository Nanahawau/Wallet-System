package com.fgt.walletsystem.services;

import com.fgt.walletsystem.dtos.InitiateTransactionDTO;
import com.fgt.walletsystem.domains.paystack.VerifyTransactionResponse;
import com.fgt.walletsystem.entities.Transaction;
import com.fgt.walletsystem.models.Response;
import org.springframework.stereotype.Service;

@Service
public interface PaymentInterface {
    Response initializeTransaction(InitiateTransactionDTO initiateTransactionDTO);
    Response verifyPayment(String reference);
    Response saveToWallet(VerifyTransactionResponse VerifyTransactionResponse);
    Response saveToTransactionLog(Transaction transaction, String email);
}
