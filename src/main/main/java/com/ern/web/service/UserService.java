package com.ern.web.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ern.web.dao.RolesDao;
import com.ern.web.dao.UserDao;
import com.ern.web.model.Roles;
import com.ern.web.model.User;

@Service("userService")
public class UserService implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private UserDao userDao;
	@Autowired
	private RolesDao rolesDao;
	
	/**
     * Get User List
     *
     */
	public List<User> getUsers(){
		return getUserDAO().list();
	}

	public void addUser(User user) {
		getUserDAO().addUser(user);
		
	} 
	
	public List<Roles> getRoles(){
		return getRolesDao().list();
	}
	/**
     * Get User DAO
     *
     * @return userDAO - User DAO
     */
    public UserDao getUserDAO() {
        return userDao;
    }

    /**
     * Get Roles DAO
     *
     * @instantiates the interface rolesDAO - RolesDAO
     */
    public RolesDao getRolesDao() {
		return rolesDao;
	}

	public void setRolesDao(RolesDao rolesDao) {
		this.rolesDao = rolesDao;
	}

	


}
