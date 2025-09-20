package com.example.transaction.controller;

import com.example.transaction.dto.request.CardRequest;
import com.example.transaction.service.abstraction.CardService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequestMapping("v1/cards")
public class CardController {
    CardService cardService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void createCard(@RequestBody CardRequest request) throws Exception{
        cardService.createCard(request);
    }
}
