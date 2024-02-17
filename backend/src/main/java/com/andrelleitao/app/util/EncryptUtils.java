package com.andrelleitao.app.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Classe responsável por realizar operações de criptografia.
 * 
 * @author André Leitão 
 * 
 */
public abstract class EncryptUtils {
	
	public static String getEncryptedSha1(final String value) {
        return DigestUtils.sha1Hex(value);
    }
    
    public static String getEncryptedSha256(final String value) {
        return DigestUtils.sha256Hex(value);
    }
    
    public static String getEncryptedSha256(final String value, final String salt) {
        return DigestUtils.sha256Hex(value.concat(salt));
    }
    
}
