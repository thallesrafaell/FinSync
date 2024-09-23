package dev.thallesrafaell.FinSync.services;

import dev.thallesrafaell.FinSync.entities.DTO.LoginRequest;
import dev.thallesrafaell.FinSync.entities.DTO.LoginResponse;
import dev.thallesrafaell.FinSync.entities.DTO.RegisterResponseDto;
import dev.thallesrafaell.FinSync.entities.DTO.ResgisterDTO;
import dev.thallesrafaell.FinSync.entities.User;
import dev.thallesrafaell.FinSync.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public LoginResponse authentication(LoginRequest dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.username(), dados.password());
        var authentication = manager.authenticate(authenticationToken);
        var token = tokenService.generateToken((User) authentication.getPrincipal());
        return new LoginResponse(token);
    }

    public RegisterResponseDto register(ResgisterDTO request) {
        var user = new User(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        var userCadastrado = userRepository.save(user);

        return new RegisterResponseDto(userCadastrado);
    }
}
