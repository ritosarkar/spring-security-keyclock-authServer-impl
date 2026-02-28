package com.springSecurity.controller;


import com.springSecurity.model.Customers;
import com.springSecurity.model.Loans;
import com.springSecurity.repository.CustomerRepository;
import com.springSecurity.repository.LoanRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class LoansController {

    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;

    @GetMapping("/myLoans")
    @PostAuthorize("hasRole('USER')")
    public List<Loans> getLoansDetails(@RequestParam String email) {
        Optional<Customers> customersOptional = customerRepository.findByEmail(email);
        return customersOptional.map(customers -> loanRepository
                .findByCustomerIdOrderByStartDtDesc(customers.getId())).orElse(null);
    }
}
