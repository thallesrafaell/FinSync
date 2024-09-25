package dev.thallesrafaell.FinSync.controllers;


import dev.thallesrafaell.FinSync.entities.DTO.LoginDTO;
import dev.thallesrafaell.FinSync.entities.DTO.LoginResponseDTO;
import dev.thallesrafaell.FinSync.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class TokenController {



    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginDTO dto){
        var response = tokenService.login(dto);
        return ResponseEntity.ok(response);
    }

}
