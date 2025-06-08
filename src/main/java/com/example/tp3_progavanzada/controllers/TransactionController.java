package com.example.tp3_progavanzada.controllers;

import com.example.tp3_progavanzada.dto.DepositRequest;
import com.example.tp3_progavanzada.dto.TransactionResponseDTO;
import com.example.tp3_progavanzada.interfaces.TransactionRetrievalService;
import com.example.tp3_progavanzada.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionRetrievalService transactionRetrievalService;

    @PostMapping("/deposit/{userId}")
    public ResponseEntity<String> deposit(@PathVariable Long userId, @RequestBody DepositRequest request){
        transactionService.deposit(userId, request.getAmount());
        return ResponseEntity.ok("Deposit success");
    }

    @PostMapping("withdraw/{userId}")
    public ResponseEntity<String> withdraw(@PathVariable Long userId, @RequestBody  DepositRequest request){
        transactionService.withdraw(userId, request.getAmount());
        return ResponseEntity.ok("Withdraw succes");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TransactionResponseDTO>> getTransactionsByUser(@PathVariable Long userId) {
        List<TransactionResponseDTO> transactions = transactionService.getTransactionsByUser(userId);
        return ResponseEntity.ok(transactions);
    }

}
