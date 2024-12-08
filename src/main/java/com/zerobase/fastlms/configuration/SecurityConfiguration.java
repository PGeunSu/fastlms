package com.zerobase.fastlms.configuration;


import com.zerobase.fastlms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final MemberService memberService;

  @Bean
  UserAuthenticationFailureHandler getFailureHandler(){
    return new UserAuthenticationFailureHandler();
  }


  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

    http
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests((auth)
        -> auth.anyRequest().permitAll());
    http.formLogin((formLogin)
            -> formLogin.loginPage("/member/login").failureHandler(getFailureHandler()).permitAll());

    http.logout((formLogout) -> formLogout.logoutRequestMatcher(
        new AntPathRequestMatcher("/member/logout"))
        .logoutSuccessUrl("/")
        .invalidateHttpSession(true));


    http.userDetailsService(memberService);


    return http.build();

  }

  @Bean
  public PasswordEncoder passwordEncoder () {

    return new BCryptPasswordEncoder();
  }




}