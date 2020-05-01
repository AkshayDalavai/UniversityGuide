package com.univeristyguide.login.security.config;

import java.util.Arrays;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import com.univeristyguide.login.security.JwtAuthenticationEntryPoint;
import com.univeristyguide.login.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Resource
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;
    
    /* Added By AkshayDalavai as it was not working in Spring 2.0*/
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }
    
    
    @Bean
    public AuditorAware<String> auditorProvider() {

		//Below we are returning is a lambda function
		//Yashu You need to work on this in order to obtain Admin or User
		/*
        if you are using spring security, you can get the currently logged username with following code segment.
        SecurityContextHolder.getContext().getAuthentication().getName()
       */
       // return () -> Optional.ofNullable("ADMIN");
    	//System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		return () -> Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getName());
    }
    
    @Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean() {
        return new JwtAuthenticationFilter();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().
                authorizeRequests()
                .antMatchers(
                        "/api/**",
                        "/token/**"
                       ).permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }
    
  
    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
