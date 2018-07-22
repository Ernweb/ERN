/**
 * 
 */
package com.ern.web.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.apache.log4j.Logger;
/**
 * @author Sandeep
 *
 */
@EnableWebSecurity
public class AppSecConfig extends WebSecurityConfigurerAdapter{
	  
	  @Autowired
	  @Qualifier("userDetailsService")
	  private UserDetailsService userDetailsService;
	  private static final Logger logger = Logger.getLogger(AppSecConfig.class);

	  @Override
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		  auth.userDetailsService(userDetailsService);
		  auth.authenticationProvider(authenticationProvider());
	  }

	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
	        http.authorizeRequests().antMatchers("/", "/list").access("hasRole('SUPERUSER') or hasRole('EMPLOYEE')")
	                .antMatchers("/newuser/**", "/delete-user-*").access("hasRole('SUPERUSER')")
	                .antMatchers("/edit-user-*").access("hasRole('SUPERUSER')")
	                .and()
	                .formLogin().loginPage("/login.xhtml")
	                .loginProcessingUrl("/login.xhtml").usernameParameter("username").passwordParameter("password")
	                .defaultSuccessUrl("/views/index.xhtml")
	                .and()
	                .exceptionHandling().accessDeniedPage("/Access_Denied")
	                .and().csrf().disable()
	                ;
	    }
	  
	  @Bean
	  public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	  };
	  
	  @Bean
	  public DaoAuthenticationProvider authenticationProvider() {
	      DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
	      authenticationProvider.setUserDetailsService(userDetailsService);
	      authenticationProvider.setPasswordEncoder(passwordEncoder());
	      if(logger.isDebugEnabled()) {
				logger.debug(authenticationProvider);
			}
	      return authenticationProvider;
	    }
	 
	  @Bean
	  public AuthenticationTrustResolver getAuthenticationTrustResolver() {
	      return new AuthenticationTrustResolverImpl();
	  }
}
