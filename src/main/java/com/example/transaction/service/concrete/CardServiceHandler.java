package com.example.transaction.service.concrete;

import com.example.transaction.dao.entity.CardEntity;
import com.example.transaction.dao.repository.CardRepository;
import com.example.transaction.dto.request.CardRequest;
import com.example.transaction.service.abstraction.CardService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CardServiceHandler implements CardService {
    CardRepository cardRepository;

    @Override
    public void createCard(CardRequest request) {

        try {
            throw new IOException();
        } catch (IOException ex) {
            throw new ArithmeticException();
        }

//        CardEntity cardEntity = buildCardEntity(request);
//        cardRepository.save(cardEntity);
    }


    private CardEntity buildCardEntity(CardRequest request) {
        return CardEntity.builder()
                .cardNumber(request.getCardNumber())
                .cvv(request.getCvv())
                .build();
    }
}
