package com.example.tp3_progavanzada.services;

import com.example.tp3_progavanzada.interfaces.TotalAmountCalculator;
import com.example.tp3_progavanzada.models.TransactionModel;
import com.example.tp3_progavanzada.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TotalAmountServiceReport implements TotalAmountCalculator {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public double calculateTotalDepositedAmount(Long userId) {
        return transactionRepository.findByAccount_User_Id(userId).stream()
                .filter(t-> t.getType().equals("DEPOSIT"))
                .mapToDouble(TransactionModel::getAmount)
                .sum();
    }

    @Override
    public double calculateTotalWithdrawnAmount(Long userId) {
        return transactionRepository.findByAccount_User_Id(userId).stream()
                .filter(t-> t.getType().equals("WITHDRAW"))
                .mapToDouble(TransactionModel::getAmount)
                .sum();
    }

}
