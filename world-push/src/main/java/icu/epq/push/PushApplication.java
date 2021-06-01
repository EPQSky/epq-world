package icu.epq.push;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Push 启动器
 *
 * @author epqsky
 */
@SpringBootApplication(scanBasePackages = {"icu.epq.common.exception", "icu.epq.push"})
@EnableDiscoveryClient
@EnableAsync
public class PushApplication {

    public static void main(String[] args) {
        SpringApplication.run(PushApplication.class, args);
    }

}
