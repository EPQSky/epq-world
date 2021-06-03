package icu.epq.common.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 加密、解密工具
 *
 * @author epqsky
 */
public class CryptUtil {

    private CryptUtil() {

    }

    /**
     * 密钥
     */
    private static final byte[] DEFAULT_SECRET_KEY = Base64.getEncoder().encode("8NJpH36GGK9a3IkyFd3l6Kb8CJkid9QJ".getBytes());

    /**
     * 初始向量 VI
     */
    private static final byte[] KEY_VI = Base64.getEncoder().encode("cs4GXOnrqLKK".getBytes());

    /**
     * 字符串信息加密
     *
     * @param info
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static String setAes256String(String info) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        SecretKeySpec keySpec = new SecretKeySpec(Base64.getDecoder().decode(DEFAULT_SECRET_KEY), "AES");
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(96, Base64.getDecoder().decode(KEY_VI));
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmParameterSpec);

        return Base64.getEncoder().encodeToString(cipher.doFinal(info.getBytes()));
    }

    /**
     * 字符串信息解密
     *
     * @param info
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static String getAes256String(String info) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        SecretKeySpec keySpec = new SecretKeySpec(Base64.getDecoder().decode(DEFAULT_SECRET_KEY), "AES");
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(96, Base64.getDecoder().decode(KEY_VI));
        cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmParameterSpec);
        return new String(cipher.doFinal(Base64.getDecoder().decode(info)));
    }

}
