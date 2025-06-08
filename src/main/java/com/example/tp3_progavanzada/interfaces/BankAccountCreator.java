package com.example.tp3_progavanzada.interfaces;

import com.example.tp3_progavanzada.models.BankAccountModel;


public interface BankAccountCreator {
    BankAccountModel openAccount(Long userId);
}
