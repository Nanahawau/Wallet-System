package com.fgt.walletsystem.services;

import com.fgt.walletsystem.models.Response;
import org.springframework.stereotype.Service;

@Service
public interface WalletInterface {
    Response getBalance(long id);
}
