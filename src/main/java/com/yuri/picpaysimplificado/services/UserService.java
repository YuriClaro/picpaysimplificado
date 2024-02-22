package com.yuri.picpaysimplificado.services;

import com.yuri.picpaysimplificado.entitites.user.User;
import com.yuri.picpaysimplificado.entitites.user.UserType;
import com.yuri.picpaysimplificado.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void validateTransfer(User payer, BigDecimal amount) {
        if (payer.getUserType() == UserType.SHOPKEEPER) {
            System.out.println("Usuário é um LOJISTA, transação não autorizada");
        }

        if(payer.getBalance().compareTo(amount) < 0) {
            System.out.println("Usuário sem saldo suficiente");
        }
    }
}
