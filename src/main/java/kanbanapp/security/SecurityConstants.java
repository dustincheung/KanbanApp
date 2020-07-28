package kanbanapp.security;

public class SecurityConstants {
	
	//Path Constants
	public static final String REGISTER_URL = "/users/**";
	public static final String H2_CONSOLE_URL = "h2-console/**";
	
	//JSON Web Token Constants
	public static final String JWT_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String JWT_SECRET = "jwtsecretkey";
	public static final long EXPIRATION_TIME = 7_200_000; //2 hour expiration time

}
