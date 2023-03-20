package com.noctus;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FraudService {
    private final FraudHistoryRepository fraudHistoryRepository;

    public Boolean isFraudster(Integer customerId) {
//        just a fake
        FraudHistory fraudHistory = FraudHistory.builder()
                .customerId(customerId)
                .isFraudster(false)
                .createdAt(LocalDateTime.now())
                .build();
        fraudHistoryRepository.save(fraudHistory);

        return fraudHistoryRepository.findFirstByCustomerId(customerId)
                .map(FraudHistory::getIsFraudster)
                .orElse(false);
    }

//    public Boolean setFraudster(Integer customerId, Boolean isFraudster) {
//
//    }
}
