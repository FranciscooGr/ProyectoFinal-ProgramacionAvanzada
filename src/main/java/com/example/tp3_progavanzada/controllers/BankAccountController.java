package com.example.tp3_progavanzada.controllers;

import com.example.tp3_progavanzada.models.BankAccountModel;
import com.example.tp3_progavanzada.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;




    @PostMapping("/open/{userId}")
    public ResponseEntity<String> openAccount(@PathVariable Long userId){
        bankAccountService.openAccount(userId);
        return ResponseEntity.ok("Account created successfully");
    }

    @GetMapping("/balance/{userId}")
    public ResponseEntity<Integer> getBalance(@PathVariable Long userId){
        System.out.println("n cuenta"+ bankAccountService.getAccountByUser(userId));
        return ResponseEntity.ok(bankAccountService.getBalance(userId));
    }

}
