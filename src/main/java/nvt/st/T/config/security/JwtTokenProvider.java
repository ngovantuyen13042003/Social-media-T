package nvt.st.T.config.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtTokenProvider {
    @Value("${com.app.jwt.jwtSecret}")
    private String jwtSecret;
    @Value("${com.app.jwt.jwtExpiration}")
    private int jwtExpiration;

    //convert secret key of JWT from Base64URL type to object SecretKey
    private SecretKey getSigninKey() {
        return Keys.hmacShaKeyFor(
                Decoders.BASE64URL.decode(jwtSecret)
        );
    }

    // generate token from username
    public String generateToken(String username) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + jwtExpiration);

        String token  = Jwts.builder()
                .setSubject(username)
                .setIssuer("T")
                .setIssuedAt(new Date())
                .setExpiration(expiration)
                .signWith(getSigninKey())
                .compact();
        return token;
    }

    // get Claims
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // general extract
    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }


    // get username
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // extract date expiration
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // check is expiration
    public boolean isExpiration(String token) {
        return extractExpiration(token).before(new Date());
    }

    // check valid
    public boolean isValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isExpiration(token);
    }

    // check validate about jwt
    public boolean validateJwtToken(String token) {
        try{
            Jwts.parserBuilder().setSigningKey(getSigninKey()).build().parse(token);
            return true;
        }catch (MalformedJwtException e){
            throw new JwtException("Invalid JWT token");
        }catch (ExpiredJwtException e) {
            throw new JwtException("Expired JWT token");
        } catch (UnsupportedJwtException e) {
            throw new JwtException("Unsupported JWT token");
        } catch (IllegalArgumentException e) {
            throw new JwtException("JWT claims string is empty");
        }
    }

}














