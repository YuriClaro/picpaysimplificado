package com.yuri.picpaysimplificado.repositories;

import com.yuri.picpaysimplificado.entitites.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
