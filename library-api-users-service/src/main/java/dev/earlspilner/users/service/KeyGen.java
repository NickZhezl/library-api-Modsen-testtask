package dev.earlspilner.users.service;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * @author Nikita Zhelezko
 */
public class KeyGen {

    private static final SecureRandom secureRandom = new SecureRandom();
    private static final int BIT_LENGTH = 256;
    private static final int RADIX = 16;

    public static String generateKey() {
        BigInteger key = new BigInteger(BIT_LENGTH, secureRandom);
        return key.toString(RADIX);
    }

    public static void main(String[] args) {
        System.out.println(generateKey());
    }

}
