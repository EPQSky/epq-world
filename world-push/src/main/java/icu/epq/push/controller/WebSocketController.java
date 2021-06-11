package icu.epq.push.controller;

import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

/**
 * websocket 中转控制器
 *
 * @author epqsky
 */
@RestController
@AllArgsConstructor
public class WebSocketController {

//    private final SimpMessagingTemplate simpMessagingTemplate;
//
//    @MessageMapping("/ws/message")
//    @KafkaListener(topics = {"${spring.kafka.template.default-topic}"})
//    public void handleMessage(String message) {
//        simpMessagingTemplate.convertAndSendToUser("epq", "/topic/message", message);
//    }

}
