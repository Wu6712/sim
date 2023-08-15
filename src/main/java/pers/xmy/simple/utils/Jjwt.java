package pers.xmy.simple.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Yao
 * @version 1.0
 */

public class Jjwt {

    //jwt过期时间，单位毫秒，这个过期时间必须大于签发时间
    private static final long EXPIRED_TIME = 24 * 60 * 60 * 1000;

    //jti唯一身份标识
    private static final String JWT_ID = UUID.randomUUID().toString();

    //加密密文，私钥
    private static final String JWT_KEY = "my-sx";

    //jwt所面向，使用jwt的用户
    private static final String SUBJECT = "my";

    //加密算法
//    private final static SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    //iss:jwt签发者 aud:接收jwt的一方
    public static String createJWT(Map<String, Object> claims, String issuer, String audience) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        HashMap<String, Object> map = new HashMap<>();
        map.put("alg","HS256");
        map.put("typ","JWT");

        Date now = new Date(System.currentTimeMillis());

        SecretKey secretKey = generalKey();

        long nowMillis = System.currentTimeMillis();

        JwtBuilder builder = Jwts.builder()
                .setHeader(map)
                .setClaims(claims)
                .setId(JWT_ID)
                //jwt的签发时间
                .setIssuedAt(now)
                .setIssuer(issuer)
                .setSubject(SUBJECT)
                .signWith(signatureAlgorithm, secretKey);
        long expTime = EXPIRED_TIME;
        if (expTime >= 0) {
            long expMillis = nowMillis + expTime;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        if (audience == null || "".equals(audience)) {
            builder.setAudience("yao");
        } else {
            builder.setAudience(audience);
        }

        return builder.compact();
    }

    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.decodeBase64(JWT_KEY);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    public static Claims verifyJwt(String token) {
        SecretKey key = generalKey();
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token).getBody();
        } catch (Exception e) {
            e.printStackTrace();
            claims = null;
        }
        return claims;

    }

    public static String generateToken(String username,String password){
        Map<String, Object> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        return createJWT( map,"jack","lisi");
    }

}
