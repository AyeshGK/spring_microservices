package com.noctus;



import com.noctus.clients.fraud.FraudCheckResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/fraud")
public class FraudController {
    private final FraudService fraudService;

    @GetMapping("/check/{id}")
    public FraudCheckResponse checkFraud(@PathVariable("id") Integer id) {
        return FraudCheckResponse
                .builder()
                .isFraudster(fraudService.isFraudster(id))
                .fraudReason("Fraud reason")
                .build();

    }
}
