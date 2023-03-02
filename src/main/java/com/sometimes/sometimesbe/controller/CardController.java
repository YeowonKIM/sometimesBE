package com.sometimes.sometimesbe.controller;

import com.sometimes.sometimesbe.dto.CardRequestDto;
import com.sometimes.sometimesbe.dto.CardResponseDto;
import com.sometimes.sometimesbe.dto.MessageResponseDto;
import com.sometimes.sometimesbe.security.UserDetailsImpl;
import com.sometimes.sometimesbe.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CardController {
    private final CardService cardService;

    @PostMapping("/cards")
    public ResponseEntity<CardResponseDto> createCard(@RequestBody CardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return cardService.createCard(requestDto, userDetails.getUser());
    }

    @GetMapping("/cards")
    public ResponseEntity<List<CardResponseDto>> getCards() {
        return cardService.getCards();
    }

    @GetMapping("/cards/{id}")
    public ResponseEntity<CardResponseDto> getCard(@PathVariable Long id) {
        return cardService.getCard(id);
    }

    @DeleteMapping("/cards/{id}")
    public ResponseEntity<MessageResponseDto> deleteCard(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cardService.deleteCard(id, userDetails.getUser());
    }

    @PutMapping("/cards/{id}")
    public ResponseEntity<CardResponseDto> updateCard(@PathVariable Long id, @RequestBody CardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cardService.updateCard(id, requestDto, userDetails.getUser());
    }

    @PutMapping("/cards/likes/{id}")
    public ResponseEntity<MessageResponseDto> createLike(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cardService.createLike(id, userDetails.getUser());
    }

}
