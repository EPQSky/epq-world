package icu.epq.pay.paycenter;

import lombok.Data;

import java.io.Serializable;

/**
 * 支付宝支付配置实体类
 *
 * @author epqsky
 */
@Data
public class AlipayCenter implements Serializable {

    private static final long serialVersionUID = 1L;

    private String protocol;
    private String gatewayHost;
    private String signType;
    private String appId;
    private String merchantPrivateKeyPath;
    private String merchantCertPath;
    private String alipayCertPath;
    private String alipayRootCertPath;

}
