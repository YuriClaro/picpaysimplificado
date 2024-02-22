package com.yuri.picpaysimplificado.repositories.dtos;

public record UserDTO(
        String name,
        String cpf,
        String email,
        String password,
        String balance,
        String userType) {
}
