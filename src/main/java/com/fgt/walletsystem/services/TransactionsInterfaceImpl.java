package com.fgt.walletsystem.services;

import com.fgt.walletsystem.entities.Transaction;
import com.fgt.walletsystem.entities.Wallet;
import com.fgt.walletsystem.enums.ResponseCode;
import com.fgt.walletsystem.models.Response;
import com.fgt.walletsystem.repositories.TransactionRepository;
import com.fgt.walletsystem.repositories.UserRepository;
import com.fgt.walletsystem.repositories.WalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class TransactionsInterfaceImpl implements TransactionsInterface {
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    public TransactionsInterfaceImpl(WalletRepository walletRepository, TransactionRepository transactionRepository) {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Response history(long id) {
        Response response = new Response();
        try {
            Optional<Wallet> wallet = walletRepository.findById(id);

            if(wallet.isPresent()) {
            Optional <List<Transaction>> transactionList = Optional.ofNullable(transactionRepository.getTransactionsByWallet(wallet.get()));
            if(transactionList.isPresent()) {
                response.setResponseCode(ResponseCode.SUCCESS.getCode());
                response.setResponseMessage(ResponseCode.SUCCESS.getMessage());
                response.setData(transactionList);
            }
            } else {
                response.setResponseCode(ResponseCode.WALLET_NOT_EXIST.getCode());
                response.setResponseMessage(ResponseCode.WALLET_NOT_EXIST.getMessage());
                return response;
            }
        } catch (Exception exception) {
            log.error("Error on get transaction history {}: ", exception.getMessage());
            exception.printStackTrace();
            response.setResponseCode(ResponseCode.ERROR.getCode());
            response.setResponseMessage(ResponseCode.ERROR.getMessage());
            return response;
        }
        return response;
    }

}
