package com.example.springboottest.service;

import com.example.springboottest.bean.User;
import com.example.springboottest.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserMapper userMapper;

    public MyUserDetailsService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userMapper.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(getAuthoritiesByType(user.getType()))
                .build();
    }

    private String[] getAuthoritiesByType(int type) {
        switch (type) {
            case 0: return new String[]{"ROLE_ADMIN", "FULL_ACCESS"};
            case 1: return new String[]{"ROLE_USER", "BASIC_ACCESS"};
            default: return new String[]{"ROLE_GUEST"};
        }
    }
}
