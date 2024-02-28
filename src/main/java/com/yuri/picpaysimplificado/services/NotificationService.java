package com.yuri.picpaysimplificado.services;

import com.yuri.picpaysimplificado.entitites.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception {
        String url = "https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6";
        ResponseEntity<String> serviceAvailable = restTemplate.getForEntity(url, String.class);

        if(!(serviceAvailable.getStatusCode() == HttpStatus.OK)) {
            System.out.println("Erro ao enviar uma notificação");
            throw new Exception("Serviço de notificação fora do ar");
        }

        user.setMessage(message);
    }
}