package pe.farell.go.backend.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import pe.farell.go.backend.security.model.entity.MainUser;

import java.util.Date;

@Slf4j
@Component
public class JwtProvider {

    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.expiration}")
    private int expiration;

    @SuppressWarnings("deprecation")
    public String generateToken(Authentication authentication) {
        MainUser mainUser = (MainUser) authentication.getPrincipal();
        return Jwts.builder().setSubject(mainUser.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000L))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJwt(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            String r = Jwts.parser().setSigningKey(secret.trim()).parseClaimsJwt(token.trim()).getBody().getSubject();

            return true;
        } catch (MalformedJwtException e) {
            log.error("Api-Token-Provider: bad formed |  ERROR: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("Api-Token-Provider: unsupported token ERROR: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("Api-Token-Provider: expired token |  ERROR: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("Api-Token-Provider: empty token |  ERROR: {}", e.getMessage());
        } catch (SignatureException e) {
            log.error("Api-Token-Provider: fake sign token |  ERROR: {}", e.getMessage());
        }
        return false;
    }

}
