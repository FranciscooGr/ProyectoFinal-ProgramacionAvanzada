package com.example.tp3_progavanzada.services;


import com.example.tp3_progavanzada.interfaces.*;
import com.example.tp3_progavanzada.models.BankAccountModel;
import com.example.tp3_progavanzada.models.UserModel;
import com.example.tp3_progavanzada.repositories.BankAccountRepository;
import com.example.tp3_progavanzada.repositories.TransactionRepository;
import com.example.tp3_progavanzada.repositories.UserRepository;
import com.example.tp3_progavanzada.util.GenerateNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class BankAccountService implements BankAccountCreator, BalanceChecker, AccountRetrievalService{
    @Autowired private UserRepository userRepository;
    @Autowired private BankAccountRepository bankAccountRepository;
    @Autowired private TransactionRepository transactionRepository;
    @Autowired private GenerateNumber generateAccountNumber;

    @Override
    public BankAccountModel openAccount(Long userId){
        UserModel user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found"));
        if(user.getBankAccountModel() != null)
            throw new RuntimeException("The user already has a bank account. ");

        BankAccountModel account = new BankAccountModel();
        account.setAccountNumber(generateAccountNumber.generateAccountNumber());
        System.out.println("N cuenta:" +account.getAccountNumber());
        account.setUser(user);
        account.setBalance(0);

        return bankAccountRepository.save(account);
    }



    @Override
    public BankAccountModel getAccountByUser(Long userId) {

        return bankAccountRepository.findByUser_Id(userId)
                .orElseThrow(() -> new RuntimeException("No bank account found for user with ID: " + userId));
    }



    @Override
    public int getBalance(Long userId) {
        BankAccountModel account = getAccountByUser(userId);
        return account.getBalance();
    }



}
