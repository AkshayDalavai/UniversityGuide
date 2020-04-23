package com.univeristyguide.login.config;



import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;




@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AppConfig implements WebMvcConfigurer{
	
	@Bean
    public AuditorAware<String> auditorProvider() {

		//Below we are returning is a lambda function
		//Yashu You need to work on this in order to obtain Admin or User
		/*
        if you are using spring security, you can get the currently logged username with following code segment.
        SecurityContextHolder.getContext().getAuthentication().getName()
       */
        return () -> Optional.ofNullable("ADMIN");
    }
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD")
                .allowCredentials(true)
        ;
    }
}
