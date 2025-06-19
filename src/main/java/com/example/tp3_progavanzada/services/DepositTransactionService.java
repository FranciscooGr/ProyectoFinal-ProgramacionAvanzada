package com.example.tp3_progavanzada.services;

import com.example.tp3_progavanzada.interfaces.AccountTransactionService;
import com.example.tp3_progavanzada.models.BankAccountModel;
import com.example.tp3_progavanzada.models.TransactionModel;
import com.example.tp3_progavanzada.repositories.BankAccountRepository;
import com.example.tp3_progavanzada.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DepositTransactionService implements AccountTransactionService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void execute(Long userId, int amount) {
        BankAccountModel account = bankAccountRepository.findByUser_Id(userId)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        account.setBalance(account.getBalance() + amount);
        bankAccountRepository.save(account);

        TransactionModel transaction = new TransactionModel();
        transaction.setAmount(amount);

        transaction.setType("DEPOSIT");
        transaction.setAccount(account);
        transactionRepository.save(transaction);
    }
}
