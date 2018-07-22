/**
 * 
 */
package com.ern.web.beans;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import com.ern.web.model.User;
import com.ern.web.service.UserService;

/**
 * @author Sandeep
 *
 */
@ManagedBean(name = "dataScrollerBean")
@RequestScoped
public class DataScrollerBean implements Serializable {

	private static final long serialVersionUID = 1L;
	List <User> users;
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
     * @return ICustomerService - Customer Service
     */
    public UserService getUserService() {
        return userService;
    }
    
    /**
     * Set User Service
     *
     * @param User ICustomerService - Customer Service
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

}
