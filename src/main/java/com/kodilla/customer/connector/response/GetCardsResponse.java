package com.kodilla.customer.connector.response;

import com.kodilla.customer.dto.CardDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetCardsResponse {
    private List<CardDto> cards;
}
