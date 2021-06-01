package icu.epq.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Auth 启动器
 *
 * @author epqsky
 */
@SpringBootApplication(scanBasePackages = {"icu.epq.common.exception", "icu.epq.auth"})
@EnableDiscoveryClient
@EnableAsync
@EnableFeignClients(basePackages = "icu.epq.user.feign")
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

}
