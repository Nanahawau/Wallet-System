package com.fgt.walletsystem.services;

import com.fgt.walletsystem.models.Response;
import org.springframework.stereotype.Service;

@Service
public interface TransactionsInterface {
    Response history(long id);
}
