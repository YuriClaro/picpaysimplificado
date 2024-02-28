package com.yuri.picpaysimplificado.controllers;

import com.yuri.picpaysimplificado.entitites.transaction.Transaction;
import com.yuri.picpaysimplificado.repositories.dtos.TransactionDTO;
import com.yuri.picpaysimplificado.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSuspensionNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transactionDTO) throws Exception {
        Transaction newTransaction = transactionService.createTransaction(transactionDTO);
        return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
    }
}
