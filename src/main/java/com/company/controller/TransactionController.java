package com.company.controller;

import com.company.dto.TransactionDTO;
import com.company.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transactionCreate")
    public ResponseEntity<?> create(@RequestBody TransactionDTO dto){
        return ResponseEntity.ok(transactionService.create(dto));
    }






}