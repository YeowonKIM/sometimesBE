package com.sometimes.sometimesbe.controller;

import com.sometimes.sometimesbe.service.CrawlingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CrawlingController {
    private final CrawlingService crawlingService;

    @GetMapping("/image")
    public List<String> getImageList() {
        return crawlingService.process();
    }
}