package com.kodilla.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDto {
    private Long id;
    private String nrb;
    private String currency;
    private BigDecimal availableFunds;

    public AccountDto(String nrb, String currency, BigDecimal availableFunds) {
        this.nrb = nrb;
        this.currency = currency;
        this.availableFunds = availableFunds;
    }
}
