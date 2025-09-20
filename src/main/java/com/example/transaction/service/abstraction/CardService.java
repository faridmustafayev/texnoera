package com.example.transaction.service.abstraction;

import com.example.transaction.dto.request.CardRequest;

public interface CardService {
    void createCard(CardRequest request);
}
