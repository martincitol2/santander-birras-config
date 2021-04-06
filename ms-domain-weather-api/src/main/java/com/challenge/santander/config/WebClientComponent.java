package com.challenge.santander.config;

import io.netty.handler.timeout.TimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.function.Consumer;

@Slf4j
@Component("webClientComponent")
public class WebClientComponent {

    public WebClient webClientBuilder(String baseURL, String defaultHeader, String userAgent) {
        return WebClient.builder().baseUrl(baseURL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, defaultHeader).defaultHeader(HttpHeaders.USER_AGENT, userAgent)
                .build();
    }

    public WebClient webClientBuilder(String baseURL, Consumer<HttpHeaders> headers, String userAgent) {
        return WebClient
                .builder()
                .baseUrl(baseURL)
                .defaultHeaders(headers)
                .defaultHeader("UserAgent", userAgent)
                .build();
    }

    public ClientResponse getResponse(WebClient webClient, String path, long timeOut) {
        ClientResponse response = null;
        try {
            response = webClient.get()
                    .uri(path)
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange()
                    .timeout(Duration.ofMillis(timeOut))
                    .block();

        } catch (TimeoutException e) {
            log.error("Time-out excedido - {} milisegundos", timeOut);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return response;
    }
}
