package com.maycowjordny.sm.application.useCase.cronjob;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CronJobUseCase {

    private final WebClient webClient;

    public CronJobUseCase(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://sm-variedades.onrender.com").build(); 
    }

    @Scheduled(cron = "0 */1 * * * *") 
    public void pingServer() {
        webClient.get()
                .uri("/ping")
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(
                        response -> System.out.println("✅ Ping realizado com sucesso! Resposta: " + response),
                        error -> System.err.println("❌ Erro ao pingar: " + error.getMessage())
                );
    }
}
