package com.company.controller;

import com.company.dto.TransactionDTO;
import com.company.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transactionCreate")
    public ResponseEntity<?> create(@RequestBody TransactionDTO dto){
        return ResponseEntity.ok(transactionService.create(dto));
    }

    @GetMapping("/paginationCardId/{cardId}")
    public ResponseEntity<?> getProfileListCardId(@RequestParam(value = "page", defaultValue = "0") int page,
                                            @RequestParam(value = "size", defaultValue = "5") int size,
                                            @PathVariable("cardId")String cardId) {
        return ResponseEntity.ok(transactionService.paginationListCardId(page, size,cardId));
    }

    @GetMapping("/paginationProfileName/{profileName}")
    public ResponseEntity<?> getProfileListProfileName(@RequestParam(value = "page", defaultValue = "0") int page,
                                            @RequestParam(value = "size", defaultValue = "5") int size,
                                            @PathVariable("profileName")String profileName) {
        return ResponseEntity.ok(transactionService.paginationListProfileName(page, size,profileName));
    }


    @GetMapping("/paginationClientId/{clientId}")
    public ResponseEntity<?> getProfileListClientId(@RequestParam(value = "page", defaultValue = "0") int page,
                                            @RequestParam(value = "size", defaultValue = "5") int size,
                                            @PathVariable("clientId")String clientId) {
        return ResponseEntity.ok(transactionService.paginationListClientId(page, size,clientId));
    }






}