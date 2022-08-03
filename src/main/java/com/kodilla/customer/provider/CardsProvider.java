package com.kodilla.customer.provider;

import com.kodilla.customer.connector.AccountsConnector;
import com.kodilla.customer.connector.CardsConnector;
import com.kodilla.customer.dto.AccountDto;
import com.kodilla.customer.dto.CardDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardsProvider {
    private final CardsConnector cardsConnector;

    @HystrixCommand(fallbackMethod = "fallbackGetCards")
    public List<CardDto> getCustomerCards(Long customerId) {
        return cardsConnector.getCards(customerId)
                .getCards()
                .stream()
                .map(card -> new CardDto(
                        card.getNumber(),
                        card.getExpirationDate(),
                        card.getSecurityCode()))
                .collect(Collectors.toList());
    }

    private List<AccountDto> fallbackGetCards(Long customerId) {
        return Collections.emptyList();
    }
}
