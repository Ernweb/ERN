package com.ern.web.config;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.faces.mvc.JsfView;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
 
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.ern.web.controller"})
public class AppMvcConfig implements WebMvcConfigurer {
 
	  @Bean ViewResolver viewResolver() { 
		  InternalResourceViewResolver resolver = new InternalResourceViewResolver(); 
		  resolver.setViewClass(JsfView.class); 
		  resolver.setPrefix("/views"); 
		  resolver.setSuffix(".xhtml"); 
		  return resolver; 
	   }
	  @Override
	   public void addResourceHandlers(ResourceHandlerRegistry registry) {

	      // Register resource handler for CSS and JS
	      registry.addResourceHandler("/resources/**").addResourceLocations("/resources/")
	            .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());

	      // Register resource handler for images
	      /*registry.addResourceHandler("/images/**").addResourceLocations("/images/")
	            .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
	            */
	   }
}