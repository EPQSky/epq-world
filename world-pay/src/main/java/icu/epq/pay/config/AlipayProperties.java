package icu.epq.pay.config;

import icu.epq.pay.paycenter.AlipayCenter;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author epqsky
 */
@Data
@ConfigurationProperties(prefix = "alipay")
public class AlipayProperties {

    private AlipayCenter config;

}
