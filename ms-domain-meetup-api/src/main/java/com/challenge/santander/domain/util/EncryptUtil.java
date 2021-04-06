package com.challenge.santander.domain.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class EncryptUtil {

    private static String key = "$2a$10$ZLhnHxdpHETcxmtEStgpI./Ri1mksgJ9iDP36FmfMdYyVg9g0b2dq";

    public static String encode(CharSequence rawPassword) {
        return BCrypt.hashpw(rawPassword.toString(), key);
    }
}
