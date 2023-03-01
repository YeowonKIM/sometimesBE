package com.sometimes.sometimesbe.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sometimes.sometimesbe.dto.SecurityExceptionDto;
import com.sometimes.sometimesbe.utils.ErrorCode;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // request 에 담긴 토큰을 가져온다.
        String token = jwtUtil.resolveToken(request);

        // 토큰이 null 이면 다음 필터로 넘어간다
        if (token == null) {
            request.setAttribute("exception", ErrorCode.NOT_FOUND_TOKEN);
            filterChain.doFilter(request, response);
            return;
        }

        // 토큰이 유효하지 않으면 다음 필터로 넘어간다
        if (!jwtUtil.validateToken(token)) {
            request.setAttribute("exception", ErrorCode.INVALID_TOKEN);
            filterChain.doFilter(request, response);
            return;
        }

        // 유효한 토큰이라면, 토큰으로부터 사용자 정보를 가져온다.
        Claims info = jwtUtil.getUserInfoFromToken(token);
        try {
            setAuthentication(info.getSubject());   // 사용자 정보로 인증 객체 만들기
        } catch (UsernameNotFoundException e) {
            request.setAttribute("exception", ErrorCode.NOT_FOUND_USER);
        }
        // 다음 필터로 넘어간다.
        filterChain.doFilter(request, response);

    }

    public void setAuthentication(String username) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = jwtUtil.createAuthentication(username);
        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);
    }

}
