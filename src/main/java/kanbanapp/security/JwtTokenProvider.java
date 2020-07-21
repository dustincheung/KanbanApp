package kanbanapp.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kanbanapp.model.User;
import static kanbanapp.security.SecurityConstants.EXPIRATION_TIME;
import static kanbanapp.security.SecurityConstants.JWT_SECRET;

@Component
public class JwtTokenProvider {
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
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
	}
	
	//validate token
	
	//obtain user id from token
}
