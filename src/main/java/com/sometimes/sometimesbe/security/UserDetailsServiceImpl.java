package com.sometimes.sometimesbe.security;

import com.sometimes.sometimesbe.entity.User;
import com.sometimes.sometimesbe.repository.UserRepository;
import com.sometimes.sometimesbe.utils.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(ErrorCode.NOT_FOUND_USER.getMsg()));
        return new UserDetailsImpl(user, user.getUsername());
    }
}
