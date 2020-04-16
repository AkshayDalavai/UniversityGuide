package com.univeristyguide.login.config;



import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;




@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AppConfig {
	
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
	
}
