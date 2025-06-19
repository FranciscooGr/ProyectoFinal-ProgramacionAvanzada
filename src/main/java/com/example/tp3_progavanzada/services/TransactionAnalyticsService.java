package com.example.tp3_progavanzada.services;

import com.example.tp3_progavanzada.dto.TransactionResponseDTO;
import com.example.tp3_progavanzada.interfaces.AccountRetrievalService;
import com.example.tp3_progavanzada.interfaces.AccountTransactionService;
import com.example.tp3_progavanzada.interfaces.TransactionAnalyticService;
import com.example.tp3_progavanzada.interfaces.TransactionRetrievalService;
import com.example.tp3_progavanzada.models.BankAccountModel;
import com.example.tp3_progavanzada.repositories.BankAccountRepository;
import com.example.tp3_progavanzada.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionAnalyticsService implements AccountRetrievalService, TransactionRetrievalService, TransactionAnalyticService {
    @Autowired private BankAccountRepository bankAccountRepository;
    @Autowired private TransactionRepository transactionRepository;



    @Override
    public BankAccountModel getAccountByUser(Long userId) {
        return bankAccountRepository.findByUser_Id(userId)
                .orElseThrow(() -> new RuntimeException("No bank account found for user with ID: " + userId));
    }



    @Override
    public List<TransactionResponseDTO> getTransactionsByUser(Long userId) {
        return transactionRepository.findByAccount_User_Id(userId).stream()
                .map(tx -> new TransactionResponseDTO(
                        tx.getId(),
                        tx.getAmount(),
                        tx.getType(),
                        tx.getTimestamp()
                ))
                .toList();
    }
    @Override
    public List<TransactionResponseDTO> getHighestTransactionsByUser(Long userId, int topN) {
        return transactionRepository.findByAccount_User_Id(userId).stream()
                .limit(topN)
                .map(tx -> new TransactionResponseDTO(
                        tx.getId(),
                        tx.getAmount(),
                        tx.getType(),
                        tx.getTimestamp()
                ))
                .toList();
    }

}
