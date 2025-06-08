package com.example.tp3_progavanzada.interfaces;

import com.example.tp3_progavanzada.models.BankAccountModel;

public interface AccountRetrievalService {
    BankAccountModel getAccountByUser(Long userId);
}
