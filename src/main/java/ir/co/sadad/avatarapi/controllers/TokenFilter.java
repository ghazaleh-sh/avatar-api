package ir.co.sadad.avatarapi.controllers;

import ir.co.sadad.avatarapi.common.exceptions.TokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.stream.JsonParsingException;
import java.io.StringReader;

import static ir.co.sadad.avatarapi.common.Const.SSN;
import static ir.co.sadad.avatarapi.common.Converter.decodeJWTBody;

/**
 * filter for extract ssn from token
 */
@Slf4j
@Component
public class TokenFilter implements WebFilter {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        if (!exchange.getRequest().getPath().pathWithinApplication().value().contains("avatar-api/api.html") &&
                !exchange.getRequest().getPath().pathWithinApplication().value().contains("swagger") &&
                !exchange.getRequest().getPath().pathWithinApplication().value().contains("/api.html") &&
                !exchange.getRequest().getPath().pathWithinApplication().value().contains("swagger-resources") &&
                !exchange.getRequest().getPath().pathWithinApplication().value().contains("actuator") &&
                !exchange.getRequest().getPath().pathWithinApplication().value().contains("v2") &&
                !exchange.getRequest().getPath().pathWithinApplication().value().contains("webjars") &&
                !exchange.getRequest().getPath().pathWithinApplication().value().contains("v3")) {
            String header = exchange.getRequest().getHeaders().getFirst("Authorization");

            if (header == null || !header.startsWith("Bearer ")) {
                throw new TokenException();
            }


            ServerHttpRequest mutatedRequest = exchange.getRequest().mutate().header(SSN, getCustomHeader(SSN, header)).build();
            ServerWebExchange mutatedExchange = exchange.mutate().request(mutatedRequest).build();
            return chain.filter(mutatedExchange);
        }
        return chain.filter(exchange);
    }


    private String getCustomHeader(String headerName, String ticket) {
        final String decodedTicket = decodeJWTBody(ticket);
        String customHeader;
        try {
            JsonReader jsonReader = Json.createReader(new StringReader(decodedTicket));
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();
            customHeader = jsonObject.getString(headerName);
        } catch (JsonParsingException x) {
            throw new IllegalArgumentException();
        }
        return customHeader;
    }
}
