package com.ern.web.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.faces.mvc.JsfView;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
 
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.ern.web.controller"})
public class AppMvcConfig implements WebMvcConfigurer {
 
	/*@Override
	  public void configureViewResolvers(ViewResolverRegistry registry) {
	    registry.scriptTemplate().prefix("/WEB-INF/views/").suffix(".xhtml");
	  }*/

	   @Bean ViewResolver viewResolver() { 
		  InternalResourceViewResolver resolver = new InternalResourceViewResolver(); 
		  resolver.setViewClass(JsfView.class); 
		  resolver.setPrefix("/views"); 
		  resolver.setSuffix(".xhtml"); 
		  return resolver; 
	   }
	   
	   /*@Override
		  public void addViewControllers(ViewControllerRegistry registry) {
		    registry.addViewController("/login").setViewName("login");
	   }*/
	   
	   @Bean
	    public SimpleControllerHandlerAdapter simpleControllerHandlerAdapter() {
	        return new SimpleControllerHandlerAdapter();
	    }

		  
}