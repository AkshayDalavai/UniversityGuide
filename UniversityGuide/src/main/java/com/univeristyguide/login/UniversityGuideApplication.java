package com.univeristyguide.login;

import java.io.IOException;

import org.apache.catalina.filters.CorsFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class UniversityGuideApplication extends SpringBootServletInitializer { 
	
	public static void main(String[] args) {
		SpringApplication.run(UniversityGuideApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(UniversityGuideApplication.class);
	}

	
	
	
	@Bean
	  public WebMvcConfigurer corsConfigurer() { return new
	  WebMvcConfigurer() {
	  
	  @Override
	  public void addCorsMappings(CorsRegistry registry) {
	  registry.addMapping("/**").allowedOrigins("*").allowedHeaders("*"); 
	  }
	};
 }
	 
	
	/*
	 * public class SimpleCORSFilter implements Filter {
	 * 
	 * public void doFilter(ServletRequest req, ServletResponse res, FilterChain
	 * chain) throws IOException, ServletException { HttpServletResponse response =
	 * (HttpServletResponse) res; response.setHeader("Access-Control-Allow-Origin",
	 * "*"); response.setHeader("Access-Control-Allow-Methods",
	 * "POST, GET, OPTIONS, DELETE"); response.setHeader("Access-Control-Max-Age",
	 * "3600"); response.setHeader("Access-Control-Allow-Headers",
	 * "x-requested-with"); chain.doFilter(req, res); } }
	 */
	
	/*
	 * @Bean public CorsConfigurationSource corsConfigurationSource() { final
	 * CorsConfiguration configuration = new CorsConfiguration();
	 * configuration.setAllowedOrigins(ImmutableList.of("*"));
	 * configuration.setAllowedMethods(ImmutableList.of(HttpMethod.HEAD.name(),
	 * HttpMethod.OPTIONS.name(), HttpMethod.GET.name(), HttpMethod.POST.name(),
	 * HttpMethod.PUT.name(), HttpMethod.DELETE.name(), HttpMethod.PATCH.name()));
	 * // setAllowCredentials(true) is important, otherwise: // The value of the
	 * 'Access-Control-Allow-Origin' header in the response must not be the wildcard
	 * '*' when the // request's credentials mode is 'include'.
	 * configuration.setAllowCredentials(false); // setAllowedHeaders is important!
	 * Without it, OPTIONS preflight request // will fail with 403 Invalid CORS
	 * request
	 * configuration.setAllowedHeaders(ImmutableList.of(HttpHeaders.AUTHORIZATION,
	 * HttpHeaders.CACHE_CONTROL, HttpHeaders.CONTENT_TYPE, HttpHeaders.ACCEPT,
	 * ORGA_ID)); final UrlBasedCorsConfigurationSource source = new
	 * UrlBasedCorsConfigurationSource(); source.registerCorsConfiguration("/**",
	 * configuration); return source; }
	 */
	
}
	
