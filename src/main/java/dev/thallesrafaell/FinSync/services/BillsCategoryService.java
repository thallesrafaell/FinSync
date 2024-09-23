package dev.thallesrafaell.FinSync.services;


import dev.thallesrafaell.FinSync.entities.BillsCategory;
import dev.thallesrafaell.FinSync.entities.DTO.BillsCategoryDTO;
import dev.thallesrafaell.FinSync.entities.DTO.BillsCategoryResponseDTO;
import dev.thallesrafaell.FinSync.entities.DTO.BillsCategoryUpdateDTO;
import dev.thallesrafaell.FinSync.exceptions.BillsCategoryAlreadyExistsException;
import dev.thallesrafaell.FinSync.repositories.BillsCategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BillsCategoryService {

    @Autowired
    private BillsCategoryRepository repository;

    public BillsCategoryResponseDTO register(String categoryName){
        var newCategory = new BillsCategory(categoryName);
        boolean categoryExists = repository.existsByName(categoryName);
        if(categoryExists) throw new BillsCategoryAlreadyExistsException("Erro: Categoria já existe no Banco de dados!");
        repository.save(newCategory);
        return  new BillsCategoryResponseDTO(newCategory.getId(), newCategory.getName());
    }

    public List<BillsCategoryDTO> listAll(){
        List<BillsCategoryDTO> list = repository.findAll()
                .stream()
                .map(bc -> new BillsCategoryDTO(bc.getName())).toList();
        return  list;
    }

    public BillsCategoryResponseDTO listByName(String categoryName){
          var category = repository.findByNameIgnoreCaseAndUnaccent(categoryName)
                .orElseThrow(()-> new EntityNotFoundException("Categoria não encontrada!"));
          return new BillsCategoryResponseDTO(category.getId(),categoryName);
    }

    public BillsCategoryResponseDTO update(BillsCategoryUpdateDTO dto) {
        var category = repository.findByNameIgnoreCaseAndUnaccent(dto.oldName())
                .orElseThrow(()-> new EntityNotFoundException("Categoria não encontrada!"));
        category.setName(dto.newName());
        repository.save(category);
        return  new BillsCategoryResponseDTO(category.getId(), category.getName());
    }

    public void delete(String name){
        var category = repository.findByNameIgnoreCaseAndUnaccent(name)
                .orElseThrow(()-> new EntityNotFoundException("Categoria não encontrada!"));
        repository.delete(category);
    }
}
