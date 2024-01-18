package com.study.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.DispatcherType;

@Configuration
public class WebSecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		//람다형태 : request -> request(security 7.0부터 람다식만 가능한걸로 바뀜)
		//예전:http.csrf().disable() 지금은 화살표함수로 바꿈
		http.csrf((csrf) -> csrf.disable())
			.cors((cors) -> cors.disable())
			.authorizeHttpRequests(request -> request
					.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()  //모두 다 허용
					.requestMatchers("/").permitAll()  //루트도 모두 들어오게 허용     //여기까지 거의 동일 아래부터 선택
					
					.requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
					.requestMatchers("/guest/**").permitAll()  					//모든사람 권한
					.requestMatchers("/member/**").hasAnyRole("USER", "ADMIN")  //1명이상 권한
					.requestMatchers("/admin/**").hasRole("ADMIN")  			//1명만 권한
					.anyRequest().authenticated()					
			);
		
		// 스프링부트에서 제공해주는 login폼 사용
		/*
		http.formLogin((formLogin)->		
						formLogin.permitAll());
		*/
		
		//내가 만든 login 폼 사용하기
		
		http.formLogin((formLogin)->formLogin
							.loginPage("/loginForm")  //로그인폼의 url (default : /login)
							.loginProcessingUrl("/login_check")  //action에 넣은 url
							.usernameParameter("username")  // 기본값(j_username)
							.passwordParameter("password")  // 기본값(j_password)
							.permitAll());
										
		
		http.logout((logout)-> logout
				.logoutUrl("/logout")
				.logoutSuccessUrl("/")
				.permitAll());
		
		return http.build();		
	}
	
	@Bean
	public UserDetailsService  users() {
		//user
		UserDetails user = User.builder()
							   .username("user")
							   .password(pEncoder().encode("1234"))
							   .roles("USER")
							   .build();
		//admin
		UserDetails admin = User.builder()
							   .username("admin")
							   .password(pEncoder().encode("1234"))
							   .roles("USER", "ADMIN")
							   .build();
		
		//메모리에 사용자 정보를 담는다
		return new InMemoryUserDetailsManager(user, admin);
	 }
		
		public PasswordEncoder pEncoder() {
			return PasswordEncoderFactories.createDelegatingPasswordEncoder();
			
		}
		
	}
	
