package dev.thallesrafaell.FinSync.services;

import dev.thallesrafaell.FinSync.entities.DTO.LoginDTO;
import dev.thallesrafaell.FinSync.entities.DTO.LoginResponseDTO;
import dev.thallesrafaell.FinSync.entities.DTO.RegisterResponseDTO;
import dev.thallesrafaell.FinSync.entities.DTO.ResgisterDTO;
import dev.thallesrafaell.FinSync.entities.User;
import dev.thallesrafaell.FinSync.exceptions.EmailAlreadyExistsException;
import dev.thallesrafaell.FinSync.exceptions.UserAlreadyExistsException;
import dev.thallesrafaell.FinSync.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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


    public LoginResponseDTO authentication(LoginDTO dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.username(), dados.password());
        var authentication = manager.authenticate(authenticationToken);
        var token = tokenService.generateToken((User) authentication.getPrincipal());
        return new LoginResponseDTO(token);
    }

    public RegisterResponseDTO register(ResgisterDTO request) {
        var userIsInDatabase = userRepository.findByUsername(request.username());
        var emailIsInDataBase = userRepository.findByEmail(request.email());

        if (userIsInDatabase != null) throw new UserAlreadyExistsException("Usuário já cadastrado!");
        if (emailIsInDataBase.isPresent()) throw new EmailAlreadyExistsException("E-mail já cadastrado!");

        var user = new User(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        var userCadastrado = userRepository.save(user);

        return new RegisterResponseDTO(userCadastrado);
    }
}
