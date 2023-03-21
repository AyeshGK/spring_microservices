package com.noctus;

import com.noctus.clients.fraud.FraudCheckResponse;
import com.noctus.clients.fraud.FraudClient;
import com.noctus.clients.notification.NotificationClient;
import com.noctus.clients.notification.NotificationRequest;
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

    private final NotificationClient notificationClient;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .build();
        customerRepository.saveAndFlush(customer);
        log.info("Customer registered: {}", customer.getId());


        try {
//            fraudCheckResponse = restTemplate.getForObject(
//                    "http://FRAUD/api/v1/fraud/check/{id}",
//                    FraudCheckResponse.class,
//                    customer.getId(),
//                    customer.getId()
//            );
            FraudCheckResponse fraudCheckResponse = fraudClient.checkFraud(customer.getId());
            if (fraudCheckResponse.getIsFraudster()) {
                throw new RuntimeException("Customer is fraudster");
            }

            log.info("Customer is not fraudster: {}", customer.getId());

            notificationClient.sendNotification(
                    NotificationRequest.builder()
                            .toCustomerId(customer.getId())
                            .toCustomerEmail(customer.getEmail())
                            .message(String.format("Hi %s; Welcome to NOCTUS services.", customer.getFirstname()))
                            .build()
            );
            log.info("Notification sent to customer: {}", customer.getId());

        } catch (Exception e) {
            log.error("Error while calling fraud service", e);
            throw new RuntimeException("Error while calling fraud service");
        }

    }
}
