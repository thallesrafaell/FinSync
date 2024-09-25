package dev.thallesrafaell.FinSync.services;


import dev.thallesrafaell.FinSync.entities.Bills;
import dev.thallesrafaell.FinSync.entities.DTO.BillResponseDTO;
import dev.thallesrafaell.FinSync.entities.DTO.BillsDTO;
import dev.thallesrafaell.FinSync.entities.DTO.BillsUpdateDTO;
import dev.thallesrafaell.FinSync.entities.User;
import dev.thallesrafaell.FinSync.entities.Wallet;
import dev.thallesrafaell.FinSync.repositories.BillsCategoryRepository;
import dev.thallesrafaell.FinSync.repositories.BillsRepository;
import dev.thallesrafaell.FinSync.repositories.UserRepository;
import dev.thallesrafaell.FinSync.repositories.WalletRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.UUID;

@Service
public class BillsService {

    @Autowired
    private BillsRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BillsCategoryRepository billsCategoryRepository;

    @Autowired
    private WalletRepository walletRepository;




    public BillResponseDTO register(BillsDTO dados, JwtAuthenticationToken token){

        var user =  userRepository.findById(UUID.fromString(token.getToken().getSubject())).orElseThrow(()-> new EntityNotFoundException("Usuário não encontrado!"));;
        var category = billsCategoryRepository.findById(dados.categoryId()).orElseThrow(()-> new EntityNotFoundException("Categoria não encontrada!"));;
        var wallet = walletRepository.findById(dados.walletId()).orElseThrow(()-> new EntityNotFoundException("Carteira não encontrada!"));

        System.out.println(user);

        var bills = new Bills(dados);
        bills.setCategory(category);
        bills.setWallet(wallet);
        var save = repository.save(bills);
        return new BillResponseDTO(save);
    }

    public List<BillResponseDTO> listAllOfWallet(JwtAuthenticationToken token) {
        var user = userRepository.findById(UUID.fromString(token.getToken().getSubject())).orElseThrow(()-> new UsernameNotFoundException("Usuário não encontrado!"));
        var walletId = user.getWallet().getId();
        List<BillResponseDTO> list = repository.findAllByWalletId(walletId)
                .stream()
                .map(BillResponseDTO::new).toList();
       return list;
    }

    public BillResponseDTO update(@RequestBody BillsUpdateDTO dados, JwtAuthenticationToken token)  {

        var user =  userRepository.findById(UUID.fromString(token.getToken().getSubject())).orElseThrow(()-> new EntityNotFoundException("Usuário não encontrado!"));
        var wallet = walletRepository.findById(user.getWallet().getId()).orElseThrow(()-> new EntityNotFoundException("Carteira não encontrada!"));
        var bills = repository.findById(dados.id()).orElseThrow(()-> new EntityNotFoundException("Conta não encontrada!"));
        var billsCategory = billsCategoryRepository.findById(dados.id());

        if(!wallet.getId().equals(bills.getWallet().getId())) throw  new IllegalArgumentException("Está conta não foi encontrada nesta sua Carteira!");

        if(dados.name() != null && dados.name().length() > 4) bills.setName(dados.name());
        if(dados.value() != null && dados.value() > 0) bills.setValue(dados.value());
        if(dados.dueDate() != null) bills.setDueDate(dados.dueDate());
        if(dados.paid() != null && dados.paid() != bills.getPaid()) bills.setPaid(dados.paid());
        billsCategory.ifPresent(bills::setCategory);

        var newBills =repository.save(bills);
        return new BillResponseDTO(newBills);
    }

    public void delete (Long id, JwtAuthenticationToken token){
        var user =  userRepository.findById(UUID.fromString(token.getToken().getSubject())).orElseThrow(()-> new EntityNotFoundException("Usuário não encontrado!"));
        var wallet = walletRepository.findById(user.getWallet().getId()).orElseThrow(()-> new EntityNotFoundException("Carteira não encontrada!"));
        var bills = repository.findById(id).orElseThrow(()-> new EntityNotFoundException("Conta não encontrada!"));

        if(wallet.getId().equals(bills.getWallet().getId())) repository.delete(bills);
    }

}
