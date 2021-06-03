package icu.epq.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import icu.epq.common.api.R;
import icu.epq.common.exception.WorldException;
import icu.epq.common.granter.BaseGranter;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * jwt 工具
 *
 * @author epqsky
 */
public class JwtUtil {

    private JwtUtil() {

    }

    /**
     * JWT密钥
     */
    private static final byte[] JWT_SECRET_KEY = Base64.getEncoder().encode("QO7uI4E6yeg9rkn95Xsahl0p9HINUJN2".getBytes());

    public static void createAccessToken(BaseGranter granter) {

        Map<String, Object> headerMap = new HashMap<>(2);
        headerMap.put("alg", "aes256");
        headerMap.put("type", "jwt");

        Calendar calendar = Calendar.getInstance();
        Date startDate = calendar.getTime();
        // 过期时间为30分钟
        calendar.add(Calendar.MINUTE, 30);
        Date endDate = calendar.getTime();

        String token = JWT.create().withHeader(headerMap)
                .withClaim("user_type", granter.getUserType())
                .withClaim("user_code", granter.getUserCode())
                .withClaim("name", granter.getName())
                .withIssuedAt(startDate)
                .withExpiresAt(endDate)
                .sign(Algorithm.HMAC256(Base64.getDecoder().decode(JWT_SECRET_KEY)));
        try {
            granter.setToken(CryptUtil.setAes256String(token));
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            throw new WorldException(R.fail("token获取异常：" + e.getMessage()));
        }
    }

    public static BaseGranter parseJwtToken(String token) {
        if (token.isEmpty()) {
            throw new WorldException(R.fail("token为空"));
        }

        String accessToken;
        try {
            accessToken = CryptUtil.getAes256String(token);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            throw new WorldException(R.fail("token获取异常：" + e.getMessage()));
        }

        Verification verify = JWT.require(Algorithm.HMAC256(Base64.getDecoder().decode(JWT_SECRET_KEY)));
        DecodedJWT jwt = verify.build().verify(accessToken);

        if (jwt.getExpiresAt().compareTo(new Date()) < 0) {
            throw new WorldException(R.fail("用户登陆已过期"));
        }

        BaseGranter granter = new BaseGranter();
        granter.setUserType(jwt.getClaim("user_type").asString());
        granter.setUserCode(jwt.getClaim("user_code").asInt());
        granter.setName(jwt.getClaim("name").asString());

        return granter;
    }

}
