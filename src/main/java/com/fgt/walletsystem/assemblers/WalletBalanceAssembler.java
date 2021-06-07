package com.fgt.walletsystem.assemblers;

import com.fgt.walletsystem.entities.Wallet;
import com.fgt.walletsystem.models.Response;
import com.fgt.walletsystem.models.WalletBalance;
import org.springframework.stereotype.Component;

@Component
public class WalletBalanceAssembler {

    public Response fromWalletObject(Wallet wallet) {
        Response response = new Response();
        WalletBalance walletBalance = new WalletBalance();
        walletBalance.setId(wallet.getId());
        walletBalance.setBalance(wallet.getBalance());
        walletBalance.setCurrency(wallet.getCurrency());

        response.setData(walletBalance);
        return response;
    }
}
