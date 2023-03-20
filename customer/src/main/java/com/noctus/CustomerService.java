package com.noctus;

import com.noctus.clients.fraud.FraudCheckResponse;
import com.noctus.clients.fraud.FraudClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .build();
        customerRepository.saveAndFlush(customer);
        log.info("Customer registered: {}", customer.getId());
        FraudCheckResponse fraudCheckResponse;

        try {
//            fraudCheckResponse = restTemplate.getForObject(
//                    "http://FRAUD/api/v1/fraud/check/{id}",
//                    FraudCheckResponse.class,
//                    customer.getId(),
//                    customer.getId()
//            );
            fraudCheckResponse = fraudClient.checkFraud(customer.getId());

            if (fraudCheckResponse.getIsFraudster()) {
                throw new RuntimeException("Customer is fraudster");
            }



        } catch (Exception e) {
            log.error("Error while calling fraud service", e);
            throw new RuntimeException("Error while calling fraud service");
        }


    }
}
