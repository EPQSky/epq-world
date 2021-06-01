package icu.epq.pay.controller;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import icu.epq.pay.config.AlipayConfig;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 支付 控制器
 *
 * @author epqsky
 */
@RestController
@AllArgsConstructor
public class PayController {

    private final AlipayConfig alipayConfig;

    @GetMapping("/pay")
    public void pay(HttpServletResponse httpServletResponse) {
        Factory.setOptions(alipayConfig.getOptions());
        try {
            // 2. 发起API调用（以创建当面付收款二维码为例）
            AlipayTradePagePayResponse response = Factory.Payment.Page().pay("AppleiPhone11128G", "2234567890", "5799.00", "http://localhost:8084/hello");
            // 3. 处理响应或异常
            httpServletResponse.setContentType("text/html;charset=utf-8");
            PrintWriter out = httpServletResponse.getWriter();
            out.write(response.getBody());
            out.flush();
            out.close();
        } catch (Exception e) {
            System.err.println("调用遭遇异常，原因：" + e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }

    }

    @GetMapping("/hello")
    public String hello() {
        return "hello,epq";
    }

}
