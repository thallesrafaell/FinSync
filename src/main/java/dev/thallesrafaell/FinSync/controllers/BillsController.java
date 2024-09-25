package dev.thallesrafaell.FinSync.controllers;

import dev.thallesrafaell.FinSync.entities.Bills;
import dev.thallesrafaell.FinSync.entities.DTO.BillsDTO;
import dev.thallesrafaell.FinSync.services.BillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/bills")
public class BillsController {

    @Autowired
    private BillsService service;

    @PostMapping
    private ResponseEntity<Bills> register(@RequestBody BillsDTO dto, UriComponentsBuilder builder, JwtAuthenticationToken token){
        var bills = service.register(dto, token);
        var uri = builder.path("/bills/{id}").buildAndExpand(bills.getId()).toUri();
        return ResponseEntity.created(uri).body(bills);
    }
}
