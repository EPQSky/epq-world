package icu.epq.paypal;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = {"icu.epq.common.exception", "icu.epq.paypal"})
@EnableDiscoveryClient
@EnableAsync
public class PaypalApplication {
}
