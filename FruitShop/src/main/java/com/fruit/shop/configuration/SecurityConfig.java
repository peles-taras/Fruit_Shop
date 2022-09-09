package com.fruit.shop.configuration;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fruit.shop.domain.Role;
import com.fruit.shop.filter.JwtAuthenticationFilter;

@SuppressWarnings("deprecation")
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();
		
		http = http.exceptionHandling()
				.authenticationEntryPoint(((requset, response, authException) -> {
			System.out.println("Unauthorized request");
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
		})).and();
		
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);	
		
		http.authorizeHttpRequests((auth) -> auth
		.antMatchers("/auth/**").permitAll()
		.antMatchers("/product/**").permitAll()
		.antMatchers("/user/**").hasAuthority(Role.ROLE_USER)
		.anyRequest().authenticated());
		
		
	}
	
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.csrf().disable()
//		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//		.authorizeHttpRequests((auth) -> auth
//		.antMatchers("/product/**").permitAll()
//		.antMatchers("/user/**").permitAll()
//		.anyRequest().authenticated())
//		.formLogin();
////		.and().addFilterBefore(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//		return http.build();
//	}

//    @Bean
//    protected AuthenticationManager authenticationManager(AuthenticationManagerBuilder auth) throws Exception {
//        return (AuthenticationManager) auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
//         
//    }

//    @Bean
//    public AuthenticationManager authManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder)
//    		throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//          .userDetailsService(userDetailsService)
//          .passwordEncoder(bCryptPasswordEncoder)
//          .and()
//          .build();
//    }

//	@Bean
//	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
//			throws Exception {
//		return authenticationConfiguration.getAuthenticationManager();
//	}
//	
//	@Bean
//	@Primary
//	public AuthenticationManagerBuilder authenticationManagerBuilder(AuthenticationManagerBuilder authenticationManagerBuilder)
//			throws Exception {
//		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//		return authenticationManagerBuilder;
//	}

}
