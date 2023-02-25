package com.sometimes.sometimesbe.service;

import com.sometimes.sometimesbe.dto.LoginRequestDto;
import com.sometimes.sometimesbe.dto.MessageResponseDto;
import com.sometimes.sometimesbe.dto.SignupRequestDto;
import com.sometimes.sometimesbe.entity.User;
import com.sometimes.sometimesbe.entity.UserRoleEnum;
import com.sometimes.sometimesbe.jwt.JwtUtil;
import com.sometimes.sometimesbe.repository.UserRepository;
import com.sometimes.sometimesbe.utils.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.sometimes.sometimesbe.utils.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    @Transactional(readOnly = true)
    public ResponseEntity<MessageResponseDto> login(LoginRequestDto loginRequestDto) {

        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new CustomException(NOT_FOUND_USER);
        }

        if (!passwordEncoder.matches(password, user.get().getPassword())) {
            throw new CustomException(NOT_MATCH_PASSWORD);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.get().getUsername(), user.get().getRole()));

        return ResponseEntity.ok()
                .body(MessageResponseDto.of("로그인 성공", HttpStatus.OK));
    }

    public ResponseEntity<MessageResponseDto> signup(SignupRequestDto signupRequestDto) {

        String username = signupRequestDto.getUsername();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        String nickname = signupRequestDto.getNickname();

        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new CustomException(DUPLICATE_USER);
        }

        UserRoleEnum role = UserRoleEnum.USER;

        if (signupRequestDto.isAdmin()) {
            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new CustomException(INVALID_ADMIN_TOKEN);
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = User.of(username, password, role, nickname);
        userRepository.save(user);
        return ResponseEntity.ok()
                .body(MessageResponseDto.of("회원가입 완료", HttpStatus.OK));
    }
}
