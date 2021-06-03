package icu.epq.gateway.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import icu.epq.gateway.provider.ResponseProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 异常处理
 *
 * @author epqsky
 */
@Slf4j
@Order(-1)
@Configuration
@RequiredArgsConstructor
public class ErrorExceptionHandler implements ErrorWebExceptionHandler {

    private final ObjectMapper objectMapper;

    @NonNull
    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, @NonNull Throwable throwable) {
        ServerHttpRequest request = serverWebExchange.getRequest();
        ServerHttpResponse response = serverWebExchange.getResponse();
        if (response.isCommitted()) {
            return Mono.error(throwable);
        }
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        if (throwable instanceof ResponseStatusException) {
            response.setStatusCode(((ResponseStatusException) throwable).getStatus());
        }
        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory bufferFactory = response.bufferFactory();
            try {
                HttpStatus status = HttpStatus.BAD_GATEWAY;
                if (throwable instanceof ResponseStatusException) {
                    status = ((ResponseStatusException) throwable).getStatus();
                }
                return bufferFactory.wrap(objectMapper.writeValueAsBytes(ResponseProvider.response(status.value(), buildMessage(request, throwable))));
            } catch (JsonProcessingException e) {
                log.error(e.getMessage());
                return bufferFactory.wrap(new byte[0]);
            }
        }));
    }

    /**
     * 构建异常信息
     */
    private String buildMessage(ServerHttpRequest request, Throwable throwable) {
        StringBuilder message = new StringBuilder("Failed to handle request [");
        message.append(request.getMethodValue());
        message.append(" ");
        message.append(request.getURI());
        message.append("]");
        if (throwable != null) {
            message.append(": ");
            message.append(throwable.getMessage());
        }
        return message.toString();
    }

}
