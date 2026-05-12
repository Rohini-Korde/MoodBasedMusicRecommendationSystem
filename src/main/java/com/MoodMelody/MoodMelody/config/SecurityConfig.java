package com.MoodMelody.MoodMelody.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
//	Injecting JwtFilter
//	This is your custom filter which checks every request for JWT token.
//	It will:
//		read Authorization header
//		validate token
//		set authentication in SecurityContext
	@Autowired
	private JwtFilter jwtFilter;

//	 This service loads user information from database using username.
//	 Example:
//	 username
//	 password
//	 role
	 
	    @Autowired
	    private CustomUserDetailService userDetailsService;

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

//	    This tells Spring Security:"Use database authentication"
	    public DaoAuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider provider =new DaoAuthenticationProvider(userDetailsService);
	       
	        provider.setPasswordEncoder(passwordEncoder());
	        return provider;
	    }

	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	        return config.getAuthenticationManager();
	    }

	    
//	    his is the main method that defines security rules.
	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	    	
//	    	CSRF protection is mainly required for browser forms.
//	    	JWT is used in REST API, so CSRF is disabled.
	        http.csrf(csrf -> csrf.disable());

	        http.authorizeHttpRequests(auth -> auth

	                // PUBLIC APIs
	                .requestMatchers("/auth/**","/error")
	                .permitAll()

	                // ADMIN ONLY APIs
	                .requestMatchers(HttpMethod.POST, "/songs/**")
	                .hasRole("ADMIN")

	                .requestMatchers(HttpMethod.PUT, "/songs/**")
	                .hasRole("ADMIN")

	                .requestMatchers(HttpMethod.DELETE, "/songs/**")
	                .hasRole("ADMIN")
	                
	                .requestMatchers(HttpMethod.DELETE, "/users/**")
	                .hasRole("ADMIN")

	                // USER + ADMIN APIs
	                .requestMatchers(
	                        "/songs/**",
	                        "/favorites/**",
	                        "/recommend/**",
	                        "/moods/**"
	                ).hasAnyRole("USER", "ADMIN")
	                
	                .requestMatchers(HttpMethod.GET, "/users/**")
	                .hasAnyRole("USER","ADMIN")

	                // Any other request
	                .anyRequest().authenticated()
	        );

//	        Spring Security will NOT create session (no HttpSession).
//	        JWT works without session.
//	        Every request must carry token.
	        http.sessionManagement(session -> session
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        );

	        
//	        This tells Spring:Use DaoAuthenticationProvider for authentication.
	        http.authenticationProvider(authenticationProvider());

//	        Run JwtFilter BEFORE default UsernamePasswordAuthenticationFilter.
//	        So JWT token will be checked first.
	        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//	        This finalizes the security setup.
	        return http.build();
	    }
	}

