package com.sometimes.sometimesbe.controller;

import com.sometimes.sometimesbe.dto.CardRequestDto;
import com.sometimes.sometimesbe.dto.CardResponseDto;
import com.sometimes.sometimesbe.entity.User;
import com.sometimes.sometimesbe.security.UserDetailsImpl;
import com.sometimes.sometimesbe.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CardController {
    private final CardService cardService;

    @PostMapping("/cards")
    public ResponseEntity<CardResponseDto> createCard(@RequestBody CardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        //Board Entity에 requestdto 안의 값들을 받아서 createBoard 작업을 한다.
        ResponseEntity<CardResponseDto> card_post =cardService.createCard(requestDto, userDetails.getUser());
        return card_post;
    }
}
