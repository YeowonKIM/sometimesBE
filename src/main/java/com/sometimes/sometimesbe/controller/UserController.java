package com.sometimes.sometimesbe.controller;

import com.sometimes.sometimesbe.dto.LoginRequestDto;
import com.sometimes.sometimesbe.dto.SignupRequestDto;
import com.sometimes.sometimesbe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        userService.login(loginRequestDto, response);
        return new ResponseEntity<String>("로그인 성공", HttpStatus.OK);
    }

    @PostMapping("/api/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
        return new ResponseEntity<String>("회원가입 성공", HttpStatus.OK);
    }
}