package dev.thallesrafaell.FinSync.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity(name = "Bills")
@Table(name = "tb_bills")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Bills {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double value;
    private LocalDateTime dueDate; // dataVencimento
    private Boolean paid; // paga

    @ManyToOne
    @JoinColumn(name = "category_id") // chave estrangeira
    private BillsCategory category; // referência à categoria de contas


    @ManyToOne
    @JoinColumn(name = "wallet_id") // chave estrangeira
    private Wallet wallet; // referência à carteira
}
