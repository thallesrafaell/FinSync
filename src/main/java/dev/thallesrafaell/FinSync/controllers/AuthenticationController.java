package dev.thallesrafaell.FinSync.controllers;

import dev.thallesrafaell.FinSync.entities.DTO.LoginResponse;
import dev.thallesrafaell.FinSync.entities.DTO.LoginRequest;
import dev.thallesrafaell.FinSync.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private LoginService service;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
       var token = service.authentication(request);
       return ResponseEntity.ok(token);
    }
}
