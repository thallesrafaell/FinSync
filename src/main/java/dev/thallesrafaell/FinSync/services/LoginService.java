package dev.thallesrafaell.FinSync.services;

import dev.thallesrafaell.FinSync.entities.DTO.LoginRequest;
import dev.thallesrafaell.FinSync.entities.DTO.LoginResponse;
import dev.thallesrafaell.FinSync.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;


    public LoginResponse authentication(LoginRequest dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.username(), dados.password());
        var authentication = manager.authenticate(authenticationToken);
        var token = tokenService.generateToken((User) authentication.getPrincipal());
        return new LoginResponse(token);
    }
}
