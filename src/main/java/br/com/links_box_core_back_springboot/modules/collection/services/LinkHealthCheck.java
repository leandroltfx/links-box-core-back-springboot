package br.com.links_box_core_back_springboot.modules.collection.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class LinkHealthCheck {

    private final WebClient webClient = WebClient.create();

    public boolean isLinkAlive(String url) {
        try {
            return Boolean.TRUE.equals(
                    webClient.head()
                            .uri(url)
                            .exchangeToMono(response ->
                                    Mono.just(response.statusCode().is2xxSuccessful())
                            )
                            .onErrorReturn(false)
                            .block() // bloqueia até obter o resultado
            );
        } catch (Exception e) {
            return false;
        }
    }

}
