package com.bree.springproject.onlinebursaryapplication.service;

import com.bree.springproject.onlinebursaryapplication.Entity.UserRegistrationTable;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    public String generateToken(UserRegistrationTable userDetails)
    {
        return Jwts
                .builder()
                .signWith(getSecreteKey())
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + (1000*60*60*24)))
                .compact();
    }

    private SecretKey getSecreteKey() {
        String SECRETE_KEY = "3c9e9b869f4133d087f4bac5f89500f7f8759a27e6e0d09de8ffa46d80011eee";
        byte[] bytes = Decoders.BASE64URL.decode(SECRETE_KEY);
        return Keys.hmacShaKeyFor(bytes);
    }

    private Claims extractAllClaims(String jwtToken){
        return Jwts
                .parser()
                .decryptWith(getSecreteKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }

    private <T> T  extractClaim(String jwtToken, Function<Claims, T> getClaim)
    {
        Claims claims = extractAllClaims(jwtToken);
        return getClaim.apply(claims);
    }

    public String extractUsername(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject);
    }

    private Date extractExpiration(String jwtToken)
    {
        return extractClaim(jwtToken, Claims::getExpiration);
    }

    private Boolean isExpired(String jwtToken)
    {
        return extractExpiration(jwtToken).before(new Date(System.currentTimeMillis()));
    }

    public Boolean isValid(UserDetails userDetails, String jwtToken)
    {
        return extractUsername(jwtToken).equals(userDetails.getUsername())
                && isExpired(jwtToken);

    }
}
