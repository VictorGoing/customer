package com.kodilla.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {
    private Long id;
    private Long customerId;
    private String number;
    private LocalDate expirationDate;
    private String securityCode;

    public CardDto(String number, LocalDate expirationDate, String securityCode) {
        this.number = number;
        this.expirationDate = expirationDate;
        this.securityCode = securityCode;
    }
}
