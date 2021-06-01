package icu.epq.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 支付 启动器
 *
 * @author epqsky
 */
@SpringBootApplication(scanBasePackages = {"icu.epq.common.exception", "icu.epq.pay"})
@EnableDiscoveryClient
@EnableAsync
public class PayApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class, args);
    }

}
