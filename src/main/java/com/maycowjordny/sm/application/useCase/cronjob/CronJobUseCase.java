package com.maycowjordny.sm.application.useCase.cronjob;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CronJobUseCase {

    private final RestTemplate restTemplate = new RestTemplate();

    @Scheduled(cron = "0 */1 * * * *")
    public void pingServer() {
        String url = "https://api-fit-caloria.onrender.com/cron";
        try {
            restTemplate.getForObject(url, String.class);
            System.out.println("Servidor pingado com sucesso.");
        } catch (Exception e) {
            System.err.println("Erro ao pingar o servidor: " + e.getMessage());
        }
    }
}
