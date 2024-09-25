package dev.thallesrafaell.FinSync.security;


import com.nimbusds.jwt.JWTClaimsSet;
import dev.thallesrafaell.FinSync.entities.DTO.LoginDTO;
import dev.thallesrafaell.FinSync.entities.DTO.LoginResponseDTO;
import dev.thallesrafaell.FinSync.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public LoginResponseDTO login (LoginDTO dto){
        var user = userRepository.findByUsername(dto.username());

        if(user.isEmpty() || !user.get().isCorrectLogin(dto,passwordEncoder)) throw new BadCredentialsException("Usuário ou senha Inválidos");
        var expireIn = 12000L;
        var now = Instant.now();

        var claims = JwtClaimsSet.builder()
                .issuer("FinSync")
                .subject(user.get().getId().toString())
                .expiresAt(now.plusSeconds(expireIn))
                .issuedAt(now)
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();git
        return new LoginResponseDTO(jwtValue);
    }
}
