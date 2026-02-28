package com.springSecurity.controller;


import com.springSecurity.model.Customers;

import com.springSecurity.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final CustomerRepository customerRepository;

    @RequestMapping("/user")
    public Customers getDetailsAfterLogin(Authentication authentication) {
        Optional<Customers> customers = customerRepository.findByEmail(authentication.getName());
        return customers.orElse(null);
    }

}
