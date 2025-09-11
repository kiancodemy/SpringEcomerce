package com.Ecommerce.main.security.jwt;
import com.Ecommerce.main.security.user.ShopUserDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;


public class JwtUtils {


    private final String secret = "my_super_secret_key_that_should_be_long_and_secure_123!";
    private final int expiration = 3600 * 1000;
    private final Key signingKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));


    public String generateTokenForUser(Authentication authentication){
        ShopUserDetails userPrinciples = (ShopUserDetails) authentication.getPrincipal();
        List<String> roles=userPrinciples.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        return Jwts.builder().setSubject("jwt").claim("id",userPrinciples.getId()).claim("roles",roles).setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime()+expiration)).signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();

    };

}
