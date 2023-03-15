package com.noctus;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService service;

    @PostMapping("/register")
    public void registerCustomer(@RequestBody CustomerRegistrationRequest request) {
        log.info("Registering customer: {}", request);
        service.registerCustomer(request);
    }
}
