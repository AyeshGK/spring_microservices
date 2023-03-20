package com.noctus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FraudHistoryRepository extends JpaRepository<FraudHistory, Integer> {
    Optional<FraudHistory> findFirstByCustomerId(Integer customerId);
}
