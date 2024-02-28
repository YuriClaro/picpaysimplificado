package com.yuri.picpaysimplificado.repositories.dtos;

import com.yuri.picpaysimplificado.entitites.user.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionDTO(
        BigDecimal amount,
        Long payerId,
        Long payeeId,
        LocalDateTime timestamp) {
}
