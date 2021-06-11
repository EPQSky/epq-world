package icu.epq.push.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

/**
 * SSE
 *
 * @author epqsky
 */
@RestController
@RequestMapping("/sse")
public class SseSocketController {

    private static final SseEmitter sseEmitter = new SseEmitter(0L);

    @GetMapping("/subscribe")
    public SseEmitter subscribe() {
        return sseEmitter;
    }

    @GetMapping("/publish")
    public void publish(String message) throws IOException {
        sseEmitter.send(message);
    }
}
