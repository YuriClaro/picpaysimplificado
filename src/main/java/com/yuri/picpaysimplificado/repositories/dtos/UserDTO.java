package com.yuri.picpaysimplificado.repositories.dtos;

import com.yuri.picpaysimplificado.entitites.user.UserType;

import java.math.BigDecimal;

public record UserDTO(
        String name,
        String cpf,
        String email,
        String password,
        BigDecimal balance,
        UserType userType,
        String message) {
}
