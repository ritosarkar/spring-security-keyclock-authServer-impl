package com.springSecurity.controller;


import com.springSecurity.model.Cards;
import com.springSecurity.model.Customers;
import com.springSecurity.repository.CardsRepository;
import com.springSecurity.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class CardsController {

    private final CardsRepository cardsRepository;
    private final CustomerRepository customerRepository;
    @GetMapping("/myCards")
    public List<Cards> getCardsDetails(@RequestParam String email){
        Optional<Customers> optionalCustomers = customerRepository.findByEmail(email);
        return optionalCustomers
                .map(cust->cardsRepository.findByCustomerId(cust.getId()))
                .orElse(null);
    }
}
