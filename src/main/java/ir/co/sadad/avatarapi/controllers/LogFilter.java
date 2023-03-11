package ir.co.sadad.avatarapi.controllers;

import ir.co.sadad.avatarapi.common.logs.BodyCaptureExchange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * filter for insert log in log management
 */
@Slf4j
@Component
@Order(-2)
public class LogFilter implements WebFilter {


    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange,
                             WebFilterChain webFilterChain) {
        BodyCaptureExchange bodyCaptureExchange = new BodyCaptureExchange(serverWebExchange);
        return webFilterChain.filter(bodyCaptureExchange).doOnSuccess((se) -> {
            log.info("Body request " + bodyCaptureExchange.getRequest().getFullBody());
            log.info("Body response " + bodyCaptureExchange.getResponse().getFullBody());
        });
    }
}
