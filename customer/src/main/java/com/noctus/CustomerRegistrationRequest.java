package com.noctus;

public record CustomerRegistrationRequest(
        String firstname,
        String lastname,
        String email
) {
}
