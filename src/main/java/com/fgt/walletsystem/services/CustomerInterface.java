package com.fgt.walletsystem.services;

import com.fgt.walletsystem.dtos.CreateWalletDTO;
import com.fgt.walletsystem.models.Response;
import org.springframework.stereotype.Service;

@Service
public interface CustomerInterface {
 Response createCustomer(CreateWalletDTO createWalletDTO);
 Response save(CreateWalletDTO createWalletDTO, String customerCode);
}
