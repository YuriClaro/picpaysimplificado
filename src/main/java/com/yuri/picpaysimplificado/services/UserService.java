package com.yuri.picpaysimplificado.services;

import com.yuri.picpaysimplificado.entitites.user.User;
import com.yuri.picpaysimplificado.entitites.user.UserType;
import com.yuri.picpaysimplificado.exceptions.SaldoInsuficienteException;
import com.yuri.picpaysimplificado.exceptions.UserTypeException;
import com.yuri.picpaysimplificado.repositories.UserRepository;
import com.yuri.picpaysimplificado.repositories.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void validateTransfer(User payer, BigDecimal amount) throws Exception{
        if (payer.getUserType() == UserType.SHOPKEEPER) {
            throw new UserTypeException("Usuário é um LOJISTA, transação não autorizada");
        }

        if(payer.getBalance().compareTo(amount) < 0) {
            throw new SaldoInsuficienteException("Usuário sem saldo suficiente");
        }
    }

    public User findUserById(Long id) throws Exception {
        return userRepository.findUserById(id).orElseThrow(() -> new Exception("Usuário inexistente"));
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User createUser(UserDTO userDTO) {
        User newUser = new User(userDTO);
        saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
