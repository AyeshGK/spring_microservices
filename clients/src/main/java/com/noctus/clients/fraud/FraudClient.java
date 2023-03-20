package com.noctus.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "fraud")
public interface FraudClient {
    @GetMapping("/api/v1/fraud/check/{id}")
    FraudCheckResponse checkFraud(@PathVariable("id") Integer id);
}
