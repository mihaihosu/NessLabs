package com.nesslabs.nesslabspring.security;

import com.nesslabs.nesslabspring.utils.exception.JwtAuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Duration;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {

    //private static final String SECRET_KEY = "2F423F4528482B4D6250655368566D597133743677397A24432646294A404E63";

    private static final long EXPIRATIONTIME = Duration.ofDays(3).toMillis();

    private final UserDetailsService userDetailsService;

    @Value("${secret}")
    private String secret;

    public JwtService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    public Authentication getAuthentication(String token) throws JwtAuthenticationException {
        String username = extractUsername(token);
        boolean isAdmin = extractIsAdmin(token);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (isTokenValid(token, userDetails)) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            if (isAdmin) {
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            } else {
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            }
            return new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
        } else {
            throw new JwtAuthenticationException("Invalid token");
        }
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);

    }

    public boolean extractIsAdmin(String token) {
        return extractClaim(token, claims -> claims.get("isAdmin", Boolean.class));
    }


    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(

            String userEmail,
            boolean isAdmin
    ){

        String jwtToken;

        jwtToken = Jwts.builder()
                .setHeaderParam("alg", "HS512")
                .setHeaderParam("typ", "JWT")
                .setSubject(userEmail)
                .claim("isAdmin", isAdmin)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ EXPIRATIONTIME))
                .signWith(getSignInKey(), SignatureAlgorithm.HS512)
                .compact();


        return jwtToken;
    }


    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        return Keys.secretKeyFor(SignatureAlgorithm.HS512);


    }
}
