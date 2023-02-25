package com.sometimes.sometimesbe.service;

import com.sometimes.sometimesbe.dto.LoginRequestDto;
import com.sometimes.sometimesbe.dto.MessageResponseDto;
import com.sometimes.sometimesbe.dto.SignupRequestDto;
import com.sometimes.sometimesbe.entity.User;
import com.sometimes.sometimesbe.entity.UserRoleEnum;
import com.sometimes.sometimesbe.jwt.JwtUtil;
import com.sometimes.sometimesbe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.bridge.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    @Transactional(readOnly = true)
    public ResponseEntity<MessageResponseDto> login(LoginRequestDto loginRequestDto, HttpServletResponse response) {

        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 유저가 없습니다.")
        );

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(), user.getRole()));
        return ResponseEntity.ok()
                .body(MessageResponseDto.of("로그인 성공", HttpStatus.OK));
    }

    public ResponseEntity<MessageResponseDto> signup(SignupRequestDto signupRequestDto) {

        String username = signupRequestDto.getUsername();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        String nickname = signupRequestDto.getNickname();

        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        UserRoleEnum role = UserRoleEnum.USER;

        if (signupRequestDto.isAdmin()) {
            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = User.of(username, password, role, nickname);
        userRepository.save(user);
        return ResponseEntity.ok()
                .body(MessageResponseDto.of("회원가입 완료", HttpStatus.OK));
    }
}
