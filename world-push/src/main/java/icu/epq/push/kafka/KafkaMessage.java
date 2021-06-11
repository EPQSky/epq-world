package icu.epq.push.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * kafka 消息消费
 *
 * @author epqsky
 */
@Component
public class KafkaMessage {

    private RestTemplate restTemplate;

    @Resource
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @KafkaListener(topics = {"${spring.kafka.template.default-topic}"})
    public void getMessage(String message) {
        System.out.println(message);
        restTemplate.getForEntity("http://localhost:8087/sse/publish?message=" + message, String.class);
    }

}
