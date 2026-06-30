package com.ecommerce.paymentservice.config;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.MessageDigest;

@Component
public class PayHereHashUtil {

    public String generateHash(String merchantId, String orderReference, BigDecimal amount, String currency, String merchantSecret) {
        try {
            String formattedAmount = String.format("%.2f", amount);
            String hashedSecret = md5(merchantSecret).toUpperCase();
            String raw = merchantId + orderReference + formattedAmount + currency + hashedSecret;
            return md5(raw).toUpperCase();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate PayHere hash");
        }
    }

    public boolean isValidNotifySignature(String merchantId,
                                          String orderId,
                                          BigDecimal amount,
                                          String currency,
                                          String statusCode,
                                          String receivedMd5sig,
                                          String merchantSecret) {
        try {
            String formattedAmount = String.format("%.2f", amount);
            String hashedSecret = md5(merchantSecret).toUpperCase();
            String raw = merchantId + orderId + formattedAmount + currency + statusCode + hashedSecret;
            String calculated = md5(raw).toUpperCase();
            return calculated.equals(receivedMd5sig);
        } catch (Exception e) {
            throw new RuntimeException("Failed to verify PayHere notify signature");
        }
    }

    private String md5(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(input.getBytes());
        StringBuilder sb = new StringBuilder();

        for (byte b : messageDigest) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }
}