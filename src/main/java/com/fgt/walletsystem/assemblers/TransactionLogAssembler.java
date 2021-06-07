package com.fgt.walletsystem.assemblers;

import com.fgt.walletsystem.dtos.InitiateTransactionDTO;
import com.fgt.walletsystem.domains.paystack.VerifyTransactionResponse;
import com.fgt.walletsystem.entities.Transaction;
import com.fgt.walletsystem.enums.TransactionType;
import com.fgt.walletsystem.utility.UtilityService;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class TransactionLogAssembler {

    public Transaction fromVerifyTransactionResponse (VerifyTransactionResponse verifyTransactionResponse) {
        Transaction transaction = new Transaction();


        transaction.setStatus(verifyTransactionResponse.getTransactionData().getStatus());
        transaction.setVerifyTransactionDate(verifyTransactionResponse.getTransactionData().getTransactionDate());
        transaction.setTransactionReference(verifyTransactionResponse.getTransactionData().getReference());
        transaction.setChannel(verifyTransactionResponse.getTransactionData().getChannel());
        transaction.setAmount(UtilityService.nairaEquivalentOfAmount(verifyTransactionResponse.getTransactionData().getAmount()));
        transaction.setCurrency(verifyTransactionResponse.getTransactionData().getCurrency());
        transaction.setFees(verifyTransactionResponse.getTransactionData().getFees());
        transaction.setIpAddress(verifyTransactionResponse.getTransactionData().getIpAddress());

        return transaction;
    }



    public Transaction fromInitiateTransactionDTO(InitiateTransactionDTO initiateTransactionDTO, String reference) {
        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.valueOf(TransactionType.class, initiateTransactionDTO.getTransactionType().toUpperCase()));
        transaction.setNarration(initiateTransactionDTO.getTransactionType());
        transaction.setMessage("Awaiting Payment Verification from Paystack");
        transaction.setPaystackReference(reference);
        transaction.setCreditWallet(UtilityService.determineCreditFromTransactionType(initiateTransactionDTO.getTransactionType()));
        transaction.setDebitWallet(UtilityService.determineDebitFromTransactionType(initiateTransactionDTO.getTransactionType()));

        return transaction;
    }


    // TODO: remove commented out code;
//
//    public static void test(String value) {
//        System.out.println(TransactionType.valueOf(TransactionType.class, value.toUpperCase(Locale.ROOT)));
//        System.out.println(UtilityService.determineDebitFromTransactionType(value));
//    }
//
//
//    public static void main(String[] args) {
//       test("payment");
//    }

}
