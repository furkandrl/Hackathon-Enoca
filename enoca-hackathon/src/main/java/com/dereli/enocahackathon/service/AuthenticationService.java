package com.dereli.enocahackathon.service;

import com.dereli.enocahackathon.dto.LoginRequest;
import com.dereli.enocahackathon.dto.TokenResponseDto;
import com.dereli.enocahackathon.security.TokenGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final CustomerService customerService;
    private final TokenGenerator tokenGenerator;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(CustomerService customerService, TokenGenerator tokenGenerator, AuthenticationManager authenticationManager) {
        this.customerService = customerService;
        this.tokenGenerator = tokenGenerator;
        this.authenticationManager = authenticationManager;
    }

    public TokenResponseDto login(LoginRequest loginRequest) {
        try {
            Authentication auth = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()));
            return TokenResponseDto.builder()
                    .accessToken(tokenGenerator.generateToken(auth))
                    .customerDto(customerService.getCustomerDto(loginRequest.getEmail()))
                    .build();
        } catch (Exception e) {
            throw new RuntimeException((String.valueOf(HttpStatus.NOT_FOUND)));
        }
    }
}
