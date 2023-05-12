package com.example.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import com.example.admin.dao.AdminDAO;
import com.example.member.dao.MemberDAO;
import com.example.security.jwt.JwtAuthenticationFilter;
import com.example.security.jwt.JwtAuthorizationFilter;
import com.example.security.service.CorsConfig;

//환경설정을 하겠다는 의미
@Configuration
@EnableWebSecurity //springSecurityFilterChain에 등록
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig {

	@Autowired
	private MemberDAO userRepository;
	
//	@Autowired
//	private AdminDAO userRepository2;

	@Autowired
	private CorsConfig corsConfig;

    @Bean
    BCryptPasswordEncoder encodePassword() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// csrf() : Cross Site Request Forgery > 사이트 간 위조 요청으로 정상적인 사용자가 의도치 않은 위조 요청을 보내는 것 > 을 막음
		http.csrf().disable();
		
		// API 사용으로 기본으로 제공하는 formLogin() 페이지를 사용하지 않음
		http.formLogin().disable();
		
		// httpbasic 기본인증 대신 jwt 사용하기 때문에 끄기
		http.httpBasic().disable();

		// session 끄기 : JWT 사용하기 때문 > 일반적인 세션은 리액트와 연결할 방법이 없다~~~~
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		// 인증 사용, security filter에 등록 @CrossOrigin (인증X)
		http.apply(new MyCustomerFilter());

		http.authorizeHttpRequests()
	    .antMatchers("/", "/images/**", "/login", "/register", "/contents/**", "/search/**", 
	    		 "/adminlogin", "/adminregister", "/selectAll",
	    		 "/comment/**", "/cast/actorProfile/**", "/cast/dirProfile/**", 
	    		 "/profile/**", "/recommend/**",
	    		 "/review", "/printmovie", "/printrandom",
	    		 "/member/signup", "/editinfo", "/emailcheck", "/genreselect", 
	    		 "/wish/**", "/rating/**", "/favorite/**", "/likes/**", "/analysis/**",
	    		 "/spoiler", "/profanity", "/checkReported/**",
	    		 "/adminpage", "/blur", "/deletereview",
	    		 "/admin/idcheck", "/admin/update").permitAll()
	    .anyRequest().authenticated();
		
		http.exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
	    
		return http.build();
		
		//"/wish/**", "/rating/**", "/review", "/analysis/**"
	}


	public class MyCustomerFilter extends AbstractHttpConfigurer<MyCustomerFilter, HttpSecurity>{
		@Override
		public void configure(HttpSecurity http) throws Exception {
			AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);

			// @CrossOrigin(인증 x), Security Filter에 등록 (인증 o)
			http.addFilter(corsConfig .corsFilter()); //응답과 관련있는 코드, 없다면 200번 에러 발생함 

			//addFilter() : FilterComparator에 등록되어 있는 Filter들을 활성화할 때 사용 
			//addFilterBefore(),addFilterAfter() : CustomFilter를 등록할 때 사용
			//인증 필터 등록
			http.addFilter(new JwtAuthenticationFilter(authenticationManager))
			//인가(권한) 필터 등록
			.addFilter(new JwtAuthorizationFilter(authenticationManager, userRepository));
		}
	}
	
//	public class MyAnotherFilter extends AbstractHttpConfigurer<MyAnotherFilter, HttpSecurity> {
//		@Override
//		public void configure(HttpSecurity http) throws Exception {
//			AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
//			System.out.println("admin");
//			// 인증 필터 등록
//			http.addFilterBefore(new AdminAuthenticationFilter(authenticationManager), JwtAuthenticationFilter.class)
//					// 인가(권한) 필터 등록
//			.addFilter(new AdminAuthorizationFilter(authenticationManager, userRepository2));
//		}
//	}
}








