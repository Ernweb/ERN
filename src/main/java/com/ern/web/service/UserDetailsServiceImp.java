/**
 * 
 */
package com.ern.web.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ern.web.dao.UserDao;
import com.ern.web.model.User;

/**
 * @author Sandeep
 *
 */
@Service("userDetailsService")
public class UserDetailsServiceImp implements UserDetailsService {
	
	private static final Logger logger = Logger.getLogger(UserDetailsServiceImp.class);
	
	@Autowired
	private UserDao userDao;

	@Transactional(readOnly = true)
	@Override
	  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	    User user = userDao.findUserByUsername(username);
	    if(logger.isDebugEnabled()){
			logger.debug("user:=="+user);
		}
		
	    if(user==null){
            logger.info("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
	    
	    UserBuilder builder = null;
	    if (user != null) {
	      builder = org.springframework.security.core.userdetails.User.withUsername(username);
	      builder.password(user.getPassword());
	      String[] roles = user.getRoles().stream().map(a -> a.getRoleCode()).toArray(String[]::new);
	      builder.roles(roles);
	    } 

	    return builder.build();
	  }
}
