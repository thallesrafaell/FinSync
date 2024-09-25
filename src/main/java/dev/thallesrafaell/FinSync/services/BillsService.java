package dev.thallesrafaell.FinSync.services;


import dev.thallesrafaell.FinSync.entities.Bills;
import dev.thallesrafaell.FinSync.entities.DTO.BillsDTO;
import dev.thallesrafaell.FinSync.entities.User;
import dev.thallesrafaell.FinSync.repositories.BillsRepository;
import dev.thallesrafaell.FinSync.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class BillsService {

    @Autowired
    private BillsRepository repository;

    private UserRepository userRepository;




    public Bills register(BillsDTO dados, JwtAuthenticationToken token){
        var user =  userRepository.findByUsername(token.getToken().getSubject());
        var bills = new Bills(dados);
        bills.setWallet(user.get().getWallet());
        repository.save(bills);
        return bills;
    }

}
