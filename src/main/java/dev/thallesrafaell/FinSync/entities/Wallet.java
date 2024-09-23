package dev.thallesrafaell.FinSync.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity(name = "Wallet")
@Table(name = "tb_wallets")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Wallet {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Double currentBalance;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Transaction> transactions;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Bills> bills;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;


}
