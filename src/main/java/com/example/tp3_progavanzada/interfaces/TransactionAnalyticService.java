package com.example.tp3_progavanzada.interfaces;

import com.example.tp3_progavanzada.dto.TransactionResponseDTO;

import java.util.List;

public interface TransactionAnalyticService {
    List<TransactionResponseDTO> getHighestTransactionsByUser(Long userId, int topN);

}
