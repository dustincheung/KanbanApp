/*
 *  This class is the entry point for our authentication. In this class we determine what is sent back to
 *  to the user when a bad login is received.  This class is utilized in SecurityConfiguration class.
 */

package kanbanapp.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import kanbanapp.Exception.BadLoginResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{
	
	//determines what is returned to if bad authentication
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		BadLoginResponse loginResponse = new BadLoginResponse();
		String jsonLoginResponse = new Gson().toJson(loginResponse);
		
		response.setContentType("application/json");
		response.setStatus(401);
		response.getWriter().print(jsonLoginResponse);
	}

}
