package icu.epq.gateway.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * jwt 工具
 *
 * @author epqsky
 */
public class JwtUtil {

    /**
     * JWT密钥
     */
    private static final byte[] JWT_SECRET_KEY = Base64.getEncoder().encode("QO7uI4E6yeg9rkn95Xsahl0p9HINUJN2".getBytes());

    public static DecodedJWT parseJwtToken(String token) {

        String accessToken;
        try {
            accessToken = CryptUtil.getAes256String(token);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            return null;
        }

        Verification verify = JWT.require(Algorithm.HMAC256(Base64.getDecoder().decode(JWT_SECRET_KEY)));

        return verify.build().verify(accessToken);
    }

}
