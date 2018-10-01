/**
 * 
 */
package com.ern.web.beans;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.springframework.dao.DataAccessException;

import com.ern.web.model.Roles;
import com.ern.web.model.User;
import com.ern.web.service.UserService;

/**
 * @author Sandeep
 *
 */
@ManagedBean(name = "userMB")
@RequestScoped
public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR   = "error";
	 

	List <User> users;
	private String id;
	private String name;
	private String password;
	private Set<Roles> roles = new HashSet<>();
	@ManagedProperty(value="#{userService}")
	private UserService userService;
	
	public List<User> getUsers(){
		ArrayList<User> userList = new ArrayList<User>();
		userList.addAll(getUserService().getUsers());
		return userList;
	}

	/**
     * Get User Service
     *
     * @return IUserService - User Service
     */
    public UserService getUserService() {
        return userService;
    }
    
    /**
     * Set User Service
     *
     * @param User IUserService - User Service
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Set User List
     *
     * @param userList List - User List
     */
    public void setUserList(List<User> users) {
        this.users = users;
    }

    
    public String addUser() {
    	try {
    		User user = new User();
    		user.setId(id);
    		user.setName(name);
    		user.setPassword(password);
    		user.setRoles(roles);
    		getUserService().addUser(user);
    		return SUCCESS;
    	}
    	catch(DataAccessException e){
		e.printStackTrace();
    	
    }
    	return ERROR;
    }

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the roles
	 */
	public Set<Roles> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}
    
    
}
