package dev.thallesrafaell.FinSync.controllers;


import dev.thallesrafaell.FinSync.entities.BillsCategory;
import dev.thallesrafaell.FinSync.entities.DTO.BillsCategoryDTO;
import dev.thallesrafaell.FinSync.entities.DTO.BillsCategoryResponseDTO;
import dev.thallesrafaell.FinSync.entities.DTO.BillsCategoryUpdateDTO;
import dev.thallesrafaell.FinSync.services.BillsCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/bills/category")
public class BillsCategoryController {

    @Autowired
    private BillsCategoryService service;

    @PostMapping
    public ResponseEntity<BillsCategoryResponseDTO> register(@RequestBody @Valid BillsCategoryDTO dados, UriComponentsBuilder builder) {

       var newCategory = service.register(dados.name());
       var uri = builder.path("/bills/category/{id}").buildAndExpand(newCategory.id()).toUri();

       return ResponseEntity.created(uri).body(newCategory);
    }

    @GetMapping
    public ResponseEntity<List<BillsCategoryDTO>> listAll(){
        var list = service.listAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{name}")
    public ResponseEntity<BillsCategoryResponseDTO> listByName(@PathVariable String name){
        var category = service.listByName(name);
        return ResponseEntity.ok(category);
    }

    @PutMapping
    public ResponseEntity<BillsCategoryResponseDTO> updadte(@RequestBody @Valid BillsCategoryUpdateDTO dto) {
        var category = service.update(dto);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> delete(@PathVariable String name){
        service.delete(name);
        return ResponseEntity.noContent().build();
    }

}
