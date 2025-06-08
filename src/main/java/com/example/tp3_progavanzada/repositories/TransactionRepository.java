package com.example.tp3_progavanzada.repositories;


import com.example.tp3_progavanzada.models.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel, Long> {
    List<TransactionModel> findByAccount_User_Id(Long userId);
}

