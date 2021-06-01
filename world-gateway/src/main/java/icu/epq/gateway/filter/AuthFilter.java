package icu.epq.gateway.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import icu.epq.gateway.provider.ResponseProvider;
import icu.epq.gateway.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * token认证过滤器
 *
 * @author epqsky
 */
@Component
@AllArgsConstructor
public class AuthFilter implements GlobalFilter, Ordered {

    private final ObjectMapper objectMapper;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        if (path.startsWith("/world-auth")) {
            return chain.filter(exchange);
        }

        ServerHttpResponse response = exchange.getResponse();
        String authorization = exchange.getRequest().getHeaders().getFirst("Authorization");
        DecodedJWT jwt = JwtUtil.parseJwtToken(authorization);
        if (jwt == null) {
            return unAuth(response, "token异常");
        }
        if (jwt.getExpiresAt().compareTo(new Date()) < 0) {
            return unAuth(response, "token过期");
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -100;
    }

    private Mono<Void> unAuth(ServerHttpResponse resp, String msg) {
        resp.setStatusCode(HttpStatus.UNAUTHORIZED);
        resp.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        String result = "";
        try {
            result = objectMapper.writeValueAsString(ResponseProvider.unAuth(msg));
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        DataBuffer buffer = resp.bufferFactory().wrap(result.getBytes(StandardCharsets.UTF_8));
        return resp.writeWith(Flux.just(buffer));
    }
}
