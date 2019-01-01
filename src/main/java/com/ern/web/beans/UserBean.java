/**
 * 
 */
package com.ern.web.beans;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.ern.web.model.User_Roles;
import com.ern.web.model.Roles;
import com.ern.web.model.User;
import com.ern.web.service.UserService;

/**
 * @author Sandeep
 *
 */
@Component
@ManagedBean(name = "userMB")
@SessionScoped
public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "index";
	private static final String ERROR   = "error";
	private static final String UPDATE   = "addUser"; 

	List <User> users;
	private User user = new User();
	private String id;
	private String name;
	private String password;
	private boolean editable = true;
	
	private List<Roles> userRoles;
	Set <User_Roles> userRoleSet;
	
	@ManagedProperty(value="#{userService}")
	private UserService userService;
	
	public List<User> getUsers(){
		ArrayList<User> userList = new ArrayList<User>();
		userList.addAll(getUserService().getUsers());
		return userList;
	}
  
	/**
	 * Delete User
	 */
	@SuppressWarnings("deprecation")
	public void deleteAction(String userId) {
		User delUser = new User();
		delUser = getUserService().getUserByCode(userId);
		getUserService().deleteUser(delUser);
		FacesMessage message= new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete ","User Information delete successfully.");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
	/**
	 * Edit UserAction
	 */
	public String editAction(String userId) {
		User updateUser = new User();
		updateUser = getUserService().getUserByCode(userId);
		id = updateUser.getId();
		name = updateUser.getName();
		password = updateUser.getPassword();
		return UPDATE;
	}
	/**
	 * Add User    
	 */
	@SuppressWarnings("deprecation")
	public String addUser() {
    	try {
    		User newuser = new User();
    		String cryptedPassword = new BCryptPasswordEncoder().encode(password); 
    		User_Roles newRoles = new User_Roles();
    		userRoles = getRoles();
    		Set <User_Roles> userRoleSet = new HashSet <User_Roles>();
    		for(int index = 0; index  < userRoles.size(); index++)
    		{
    		newRoles.setUser(newuser);
    		newRoles.setRoleCode(userRoles.get(index).getRoleCode());
    		userRoleSet.add(newRoles);
    		}
    		
    		newuser.setId(id);
    		newuser.setName(name);
    		newuser.setPassword(cryptedPassword);
    		newuser.setRoles(userRoleSet);
    		getUserService().addUser(newuser);
    		FacesMessage message= new FacesMessage(FacesMessage.SEVERITY_INFO, "Save ","User Information saved successfully.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
            return SUCCESS;
    	}
    	catch(DataAccessException e){
		e.printStackTrace();
		FacesMessage message= new FacesMessage(FacesMessage.SEVERITY_INFO, "Save ","Failed to save  User Information .");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    
    	return ERROR;
    	}
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
	public List<Roles> getRoles() {
		ArrayList<Roles> roleList = new ArrayList<Roles>();
		roleList.addAll(getUserService().getRoles());
		return roleList;
	}

	/**
	 * @param userRole the userRole to set
	 */
	public void setRoles(Set<User_Roles> userRoleSet) {
		this.userRoleSet = userRoleSet;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the userRoles
	 */
	public List<Roles> getUserRoles() {
		return userRoles;
	}

	/**
	 * @param userRoles the userRoles to set
	 */
	public void setUserRoles(List<Roles> userRoles) {
		this.userRoles = userRoles;
	}

	public boolean getEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}    
    
}
