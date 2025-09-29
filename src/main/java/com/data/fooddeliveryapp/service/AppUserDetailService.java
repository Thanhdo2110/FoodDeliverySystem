package com.data.fooddeliveryapp.service;

import com.data.fooddeliveryapp.entity.UserEntity;
import com.data.fooddeliveryapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.expression.ExpressionException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
@AllArgsConstructor
public class AppUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user= userRepository.findByEmail(email)
                .orElseThrow(()-> new Exception("User not found"));
        return new User(user.getEmail(),user.getPassword(), Collections.emptyList());
    }
}
