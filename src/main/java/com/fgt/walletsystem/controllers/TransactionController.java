package com.fgt.walletsystem.controllers;

import com.fgt.walletsystem.models.Response;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fgt.walletsystem.services.TransactionsInterface;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/transaction")
@Validated
public class TransactionController {

    private final TransactionsInterface transactionsInterface;

    public TransactionController(TransactionsInterface transactionsInterface) {
        this.transactionsInterface = transactionsInterface;
    }

    @GetMapping("/history")
    public ResponseEntity<Response> history (@RequestParam @Valid long id) {
        return ResponseEntity.ok(transactionsInterface.history(id));
    }
}
