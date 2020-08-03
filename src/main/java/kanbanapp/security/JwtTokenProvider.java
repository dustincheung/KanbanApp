/*
 *  This class contains JWT utility functions. Methods handle generating token, validating token,
 *  and getting user info from token.
 */

package kanbanapp.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import kanbanapp.model.User;
import static kanbanapp.security.SecurityConstants.EXPIRATION_TIME;
//import static kanbanapp.security.SecurityConstants.JWT_SECRET;

@Component
public class JwtTokenProvider {
	
	//jwtsecret comes from a custom configuration in both dev and prod spring profiles
	@Value("${jwtsecret}")
    private String jwtSecret;
	
	//generate token
	public String generateToken(Authentication authentication) {
		//obtain curr user, date, and calculate expiration time
		User user = (User)authentication.getPrincipal();
		Date currTime = new Date(System.currentTimeMillis());
		Date expireTime = new Date(currTime.getTime() + EXPIRATION_TIME);
		
		//get string version of userId
		String userId = Long.toString(user.getId());
		
		//information you also want to pass in token (you can also pass role in here too)
		Map<String,Object> claims = new HashMap<>();
        claims.put("id", (Long.toString(user.getId())));
        claims.put("username", user.getUsername());
        claims.put("name", user.getName());
        
        //return built jwt
        return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(currTime)
                .setExpiration(expireTime)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
	}
	
	//validate token
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		}catch (MalformedJwtException e){
            System.out.println("JWT Token is invalid");
		}catch (SignatureException e){
            System.out.println("JWT Signature is invalid");
        }catch (ExpiredJwtException e){
            System.out.println("JWT token is expired");
        }catch (UnsupportedJwtException e){
            System.out.println("Unsupported JWT token");
        }catch (IllegalArgumentException e){
            System.out.println("JWT claims string is empty");
        }
        return false;
	}
	
	//obtain user id from token, parsing out user information from token
	public Long getUserIdFromJWT(String token){
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        
        //parse id
        String id = (String)claims.get("id");

        return Long.parseLong(id);
    }
}
