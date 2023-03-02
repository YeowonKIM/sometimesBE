package com.sometimes.sometimesbe.controller;

import com.sometimes.sometimesbe.service.CrawlingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CrawlingController {
    private final CrawlingService crawlingService;

    @PostMapping("/image")
    public ResponseEntity<List<String>> createImage() {
        return crawlingService.createImage();
    }
}