package com.data.fooddeliveryapp.controller;


import com.data.fooddeliveryapp.io.AuthenticationRequest;
import com.data.fooddeliveryapp.io.AuthenticationResponse;
import com.data.fooddeliveryapp.service.AppUserDetailService;
import com.data.fooddeliveryapp.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;
    private final AppUserDetailService userDetailService;

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest request){
          authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        final UserDetails userDetails = userDetailService.loadUserByUsername(request.getEmail());
        final String jwtToken=jwtUtil.generateToken(userDetails);
        return new AuthenticationResponse(request.getEmail(), jwtToken);
    }
}
