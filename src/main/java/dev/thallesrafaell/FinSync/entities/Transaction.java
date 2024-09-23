package dev.thallesrafaell.FinSync.entities;

import dev.thallesrafaell.FinSync.entities.enums.TypeTransaction;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Transaction")
@Table(name = "tb_transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Enumerated(EnumType.STRING)
        private TypeTransaction typeTransaction;
        private Double value;
        private LocalDateTime date;
        private String title;

        @ManyToOne
        @JoinColumn(name = "wallet_id") // chave estrangeira
        private Wallet wallet;
}
