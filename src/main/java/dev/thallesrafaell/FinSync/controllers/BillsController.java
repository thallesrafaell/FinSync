package dev.thallesrafaell.FinSync.controllers;

import dev.thallesrafaell.FinSync.entities.Bills;
import dev.thallesrafaell.FinSync.entities.DTO.BillResponseDTO;
import dev.thallesrafaell.FinSync.entities.DTO.BillsDTO;
import dev.thallesrafaell.FinSync.entities.DTO.BillsUpdateDTO;
import dev.thallesrafaell.FinSync.services.BillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/bills")
public class BillsController {

    @Autowired
    private BillsService service;

    @PostMapping
    private ResponseEntity<BillResponseDTO> register(@RequestBody BillsDTO dto, UriComponentsBuilder builder, JwtAuthenticationToken token){
        var bills = service.register(dto, token);
        var uri = builder.path("/bills/{id}").buildAndExpand(bills.id()).toUri();
        return ResponseEntity.created(uri).body(bills);
    }

    @GetMapping
    private ResponseEntity<List<BillResponseDTO>> listAllOfUser(JwtAuthenticationToken token){
       var list = service.listAllOfWallet(token);
       return ResponseEntity.ok(list);
    }

    @PutMapping
    public  ResponseEntity<BillResponseDTO> update(@RequestBody BillsUpdateDTO dto, JwtAuthenticationToken token){
       var bills = service.update(dto, token);
       return ResponseEntity.ok(bills);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delelte(@PathVariable Long id, JwtAuthenticationToken token){
        service.delete(id,token);

        return ResponseEntity.noContent().build();
    }

}
