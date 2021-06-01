package icu.epq.pay.config;

import com.alipay.easysdk.kernel.Config;
import icu.epq.common.api.R;
import icu.epq.common.exception.WorldException;
import icu.epq.pay.paycenter.AlipayCenter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 支付宝支付配置
 *
 * @author epqsky
 */
@Configuration
@EnableConfigurationProperties(AlipayProperties.class)
public class AlipayConfig {

    private final AlipayProperties properties;

    public AlipayConfig(AlipayProperties properties) {
        this.properties = properties;
    }

    public Config getOptions() {
        Config config = new Config();
        AlipayCenter alipayCenter = properties.getConfig();

        config.protocol = alipayCenter.getProtocol();
        config.gatewayHost = alipayCenter.getGatewayHost();
        config.signType = alipayCenter.getSignType();
        config.appId = alipayCenter.getAppId();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(alipayCenter.getMerchantPrivateKeyPath())))) {
            config.merchantPrivateKey = reader.readLine();
        } catch (IOException e) {
            throw new WorldException(R.fail("支付宝配置异常：" + e.getMessage()));
        }
        config.merchantCertPath = alipayCenter.getMerchantCertPath();
        config.alipayCertPath = alipayCenter.getAlipayCertPath();
        config.alipayRootCertPath = alipayCenter.getAlipayRootCertPath();
        return config;
    }

}
