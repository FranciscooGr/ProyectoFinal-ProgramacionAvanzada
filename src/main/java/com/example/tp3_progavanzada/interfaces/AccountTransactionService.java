package com.example.tp3_progavanzada.interfaces;

import com.example.tp3_progavanzada.models.BankAccountModel;

public interface AccountTransactionService {
    BankAccountModel getAccountByUser(Long userId);
    void deposit(Long userId, int amount);
    void withdraw(Long userId, int amount);
}
