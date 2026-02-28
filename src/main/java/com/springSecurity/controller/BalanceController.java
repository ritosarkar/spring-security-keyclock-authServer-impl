package com.springSecurity.controller;


import com.springSecurity.model.AccountTransactions;
import com.springSecurity.model.Customers;
import com.springSecurity.repository.AccountTransactionsRepository;
import com.springSecurity.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class BalanceController {

    private final AccountTransactionsRepository accountTransactionsRepository;
    private final CustomerRepository customerRepository;

    @GetMapping("/myBalance")
    public List<AccountTransactions> getBalanceDetails(@RequestParam String email) {
        Optional<Customers> optionalCustomers = customerRepository.findByEmail(email);
        return optionalCustomers
                .map(customers -> accountTransactionsRepository
                        .findByCustomerIdOrderByTransactionDtDesc(customers.getId())).orElse(null);
    }
}
