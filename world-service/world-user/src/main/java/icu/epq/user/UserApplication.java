package icu.epq.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * user 启动器
 *
 * @author wangtian
 */
@SpringBootApplication(scanBasePackages = {"icu.epq.common.exception", "icu.epq.user"})
@EnableDiscoveryClient
@EnableAsync
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
