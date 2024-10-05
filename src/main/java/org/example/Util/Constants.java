package org.example.Util;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class Constants {
    private static final String SECRET_KEY = "mySecretKeyForJwtTokenWhichIsVerySecret";
    public static final Key key = new SecretKeySpec(SECRET_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());
}
