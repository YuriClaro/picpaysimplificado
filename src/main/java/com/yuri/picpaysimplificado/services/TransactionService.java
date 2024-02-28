package com.yuri.picpaysimplificado.services;

import com.yuri.picpaysimplificado.entitites.transaction.Transaction;
import com.yuri.picpaysimplificado.entitites.user.User;
import com.yuri.picpaysimplificado.repositories.TransactionRepository;
import com.yuri.picpaysimplificado.repositories.dtos.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationService;

    public Transaction createTransaction(TransactionDTO transactionDTO) throws Exception {
        User payer = userService.findUserById(transactionDTO.payerId());
        User payee = userService.findUserById(transactionDTO.payeeId());

        userService.validateTransfer(payer, transactionDTO.amount());

        boolean isAuthorized = authorizedTransfer(payer, transactionDTO.amount());

        if(!isAuthorized) {
            throw new Exception("Serviço não autorizado!");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transactionDTO.amount());
        newTransaction.setPayer(payer);
        newTransaction.setPayee(payee);
        newTransaction.setTimestamp(LocalDateTime.now());

        payer.setBalance(payer.getBalance().subtract(transactionDTO.amount()));
        payee.setBalance(payee.getBalance().add(transactionDTO.amount()));

        transactionRepository.save(newTransaction);
        userService.saveUser(payer);
        userService.saveUser(payee);

        notificationService.sendNotification(payer, "Transação bem sucedida, dinheiro enviado");
        notificationService.sendNotification(payee, "Transação bem sucedida, dinheiro recebido");


        return newTransaction;
    }

    public boolean authorizedTransfer(User payer, BigDecimal value) {
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", Map.class);

        if (authorizationResponse.getStatusCode() == HttpStatus.OK) {
            String message = (String) authorizationResponse.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        } else {
            return false;
        }
    }
}