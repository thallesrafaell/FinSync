package dev.thallesrafaell.FinSync.entities;


import dev.thallesrafaell.FinSync.entities.DTO.LoginDTO;
import dev.thallesrafaell.FinSync.entities.DTO.ResgisterDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;
import java.util.UUID;

@Entity(name = "user")
@Table(name = "tb_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class User  {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String username;
    private String email;
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL) // referência inversa
    private Wallet wallet; // carteira associada ao usuário

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    public User(ResgisterDTO request) {
        this.name = request.name();
        this.username = request.username();
        this.email = request.email();
    }


    public boolean isCorrectLogin(LoginDTO dto, BCryptPasswordEncoder passwordEncoder) {
       return passwordEncoder.matches(dto.password(), this.password);
    }
}
