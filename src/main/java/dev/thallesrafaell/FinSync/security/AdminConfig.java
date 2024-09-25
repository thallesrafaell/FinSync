package dev.thallesrafaell.FinSync.security;


import dev.thallesrafaell.FinSync.entities.Role;
import dev.thallesrafaell.FinSync.entities.User;
import dev.thallesrafaell.FinSync.repositories.RoleRepository;
import dev.thallesrafaell.FinSync.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
public class AdminConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        var roleAdmin = roleRepository.findByName(Role.Values.ADMIN.name());

        var userAdmin = userRepository.findByUsername("admin");
        userAdmin.ifPresentOrElse(
                user -> {
                    System.out.println("admin já existe!");
                },
                () -> {
                    var newUser = new User();
                    newUser.setName("admin");
                    newUser.setUsername("admin");
                    newUser.setEmail("admin@admin.com");
                    newUser.setPassword(passwordEncoder.encode("123456"));
                    newUser.setRoles(Set.of(roleAdmin.get()));
                    userRepository.save(newUser);
                }
        );

    }
}
