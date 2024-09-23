package dev.thallesrafaell.FinSync.services;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import dev.thallesrafaell.FinSync.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token =  JWT.create()
                    .withIssuer("FinSync")
                    .withSubject(user.getUsername())
                    .withExpiresAt(dateExpires())
                    .sign(algorithm);

            System.out.println("Generated token: " + token);
            return token;

        } catch (JWTVerificationException exception){
            throw new RuntimeException("Error generating the token!",exception);
        }
    }


    public String getSubject(String jwtToken){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("FinSync")
                    .build()
                    .verify(jwtToken)
                    .getSubject();
        } catch (JWTVerificationException exception){
            System.err.println("Invalid token: " + jwtToken);
            throw new RuntimeException("Error invalid Token!");
        }
    }


    private Instant dateExpires() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
