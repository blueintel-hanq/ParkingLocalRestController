package com.blueparking.rest.config.oauth;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class ClientGenerator {

    public static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static byte[]  getEncodedHash(String originalString) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(originalString.getBytes(StandardCharsets.UTF_8));
    }

    public static String makeUUID(int radix) {
        UUID uuid = UUID.randomUUID();
        long l = ByteBuffer.wrap(uuid.toString().getBytes()).getLong();
        String str = Long.toString(l, radix);
        return str;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        //String clientId = "testId";
        //String secret = "testSecret";
        //String clientId = "e46d628e42fa4d46a9c84423c38d2a2a";
        //String secret =  "001EE61BEC9FA2CC27BBCE9623AAE6FB2E36616C81823A05A570069854C9999A";

        String clientId = UUID.randomUUID().toString().replaceAll("-", "");
        String secret = bytesToHex(getEncodedHash(makeUUID(32))).toUpperCase();

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        System.out.println( "clientId:"+clientId
                + ",  decode secret:" + secret
                + ",  encode secret:" + passwordEncoder.encode(secret)
        );

    }

}
