/*
 *  This class allows us to configure/customize Spring Security for our app.
 */
package kanbanapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		securedEnabled = true,
		prePostEnabled = true,
		jsr250Enabled = true
)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JWTAuthenticationEntryPoint badLoginHandler;
	
	//injected into UserService class to encode password
	@Bean
	BCryptPasswordEncoder bCrypePasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.cors().and().csrf().disable() 		//allow cross origin access and disable cross site req forgery token b/c we are using jwt
			.exceptionHandling().authenticationEntryPoint(badLoginHandler).and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS) //server is stateless, redux holds our state client side
			.and()
			.headers().frameOptions().sameOrigin() //enable H2 database
			.and()
			.authorizeRequests()
			.antMatchers(		//we don't need to be logged in to access these files, needed if rendering guis server side (such as Thymeleaf)
                    "/",
                    "/favicon.ico",
                    "/**/*.png",
                    "/**/*.gif",
                    "/**/*.svg",
                    "/**/*.jpg",
                    "/**/*.html",
                    "/**/*.css",
                    "/**/*.js"
            ).permitAll()
			.antMatchers("/users/**").permitAll()
            .anyRequest().authenticated();
	}
}
