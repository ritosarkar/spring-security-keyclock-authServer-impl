package com.springSecurity.controller;


import com.springSecurity.model.Accounts;
import com.springSecurity.model.Customers;
import com.springSecurity.repository.AccountsRepository;
import com.springSecurity.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class AccountController {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;

    @GetMapping("/myAccount")
    public Accounts getAccountDetails(@RequestParam String email) {
        Optional<Customers> optionalCustomers = customerRepository.findByEmail(email);
        return optionalCustomers
                .map(customers -> accountsRepository
                        .findByCustomerId(customers.getId())).orElse(null);
    }
}
