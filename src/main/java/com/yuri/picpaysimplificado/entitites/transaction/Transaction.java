package com.yuri.picpaysimplificado.entitites.transaction;

import com.yuri.picpaysimplificado.entitites.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name="payer_id")
    private User payer;
    @ManyToOne
    @JoinColumn(name="payee_id")
    private User payee;
    private LocalDateTime timestamp;
}
