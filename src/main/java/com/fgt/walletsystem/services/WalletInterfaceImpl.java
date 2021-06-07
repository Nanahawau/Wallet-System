package com.fgt.walletsystem.services;

import com.fgt.walletsystem.assemblers.WalletBalanceAssembler;
import com.fgt.walletsystem.entities.Wallet;
import com.fgt.walletsystem.enums.ResponseCode;
import com.fgt.walletsystem.models.Response;
import com.fgt.walletsystem.repositories.WalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Slf4j
@Service
public class WalletInterfaceImpl implements WalletInterface{

    private final WalletRepository walletRepository;
    private final WalletBalanceAssembler walletBalanceAssembler;

    public WalletInterfaceImpl(WalletRepository walletRepository, WalletBalanceAssembler walletBalanceAssembler) {
        this.walletRepository = walletRepository;
        this.walletBalanceAssembler = walletBalanceAssembler;
    }

    @Override
    public Response getBalance(long id) {
        Response response = new Response();
        try {
            Optional<Wallet> wallet = walletRepository.findWalletById(id);
            if (!wallet.isPresent()) {
                response.setResponseCode(ResponseCode.WALLET_NOT_EXIST.getCode());
                response.setResponseMessage(ResponseCode.WALLET_NOT_EXIST.getMessage());
                return response;
            }

            response = walletBalanceAssembler.fromWalletObject(wallet.get());
            response.setResponseCode(ResponseCode.SUCCESS.getCode());
            response.setResponseMessage(ResponseCode.SUCCESS.getMessage());

        } catch (Exception exception) {
            log.error("Error on get wallet balance {}: ", exception.getMessage());
            response.setResponseCode(ResponseCode.ERROR.getCode());
            response.setResponseMessage(ResponseCode.ERROR.getMessage());
            return response;
        }
        return response;
    }
}
