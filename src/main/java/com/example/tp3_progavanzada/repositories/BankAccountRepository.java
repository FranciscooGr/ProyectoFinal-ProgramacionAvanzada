package com.example.tp3_progavanzada.repositories;

import com.example.tp3_progavanzada.models.BankAccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface BankAccountRepository extends JpaRepository<BankAccountModel, Long> {
    Optional<BankAccountModel> findByUser_Id(Long userId);
}

