package com.example.elearning.models;

import java.security.SecureRandom;
import java.util.Base64;

public class ActivationTokenGenerator {

    public static String generateToken() {
        byte[] randomBytes = new byte[32];
        new SecureRandom().nextBytes(randomBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }


}
