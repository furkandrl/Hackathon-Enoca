package com.dereli.enocahackathon.security;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dereli.enocahackathon.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

@Component
public class TokenGenerator {
    private final UserDetailsServiceImpl userDetails;
    @Value("${jwt-variables.KEY}")
    private String KEY;
    @Value("${jwt-variables.ISSUER}")
    private String ISSUER;
    @Value("${jwt-variables.EXPIRES_ACCESS_TOKEN_MINUTE}")
    private long EXPIRES_ACCESS_TOKEN_MINUTE;

    public TokenGenerator(UserDetailsServiceImpl userDetails) {
        this.userDetails = userDetails;
    }

    public String generateToken(Authentication authentication){
        String email = ((UserDetails) authentication.getPrincipal()).getUsername();
        return JWT.create()
                .withSubject(email)
                .withExpiresAt(new Date(System.currentTimeMillis()
                        +(EXPIRES_ACCESS_TOKEN_MINUTE*60*1000)))
                .withIssuer(ISSUER)
                .sign(Algorithm.HMAC256(KEY.getBytes()));
    }

    public DecodedJWT verifyJWT(String token){
        Algorithm algorithm = Algorithm.HMAC256(KEY.getBytes());
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        try {
            return verifier.verify(token);
        } catch (Exception e) {
            throw new RuntimeException(String.valueOf(HttpStatus.BAD_REQUEST));
        }
    }
}
