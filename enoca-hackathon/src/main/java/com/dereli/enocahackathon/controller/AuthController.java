package com.dereli.enocahackathon.controller;

import com.dereli.enocahackathon.dto.CustomerDto;
import com.dereli.enocahackathon.dto.LoginRequest;
import com.dereli.enocahackathon.dto.SaveCustomerRequest;
import com.dereli.enocahackathon.dto.TokenResponseDto;
import com.dereli.enocahackathon.model.Customer;
import com.dereli.enocahackathon.service.AuthenticationService;
import com.dereli.enocahackathon.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationService authenticationService;
    private final CustomerService customerService;

    public AuthController(AuthenticationService authenticationService, CustomerService customerService) {
        this.authenticationService = authenticationService;
        this.customerService = customerService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity
                .ok(authenticationService.login(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<CustomerDto> register(@RequestBody SaveCustomerRequest request){
        return ResponseEntity
                .ok(customerService.createCustomer(request));
    }

}
