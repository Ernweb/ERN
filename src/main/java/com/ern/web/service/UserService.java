package com.ern.web.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ern.web.dao.UserDao;
import com.ern.web.model.User;

@Service("userService")
@Transactional(readOnly = true)
public class UserService implements Serializable {
	private static final long serialVersionUID = 1L;
	@Autowired
	private UserDao userDao;
	
	/**
     * Get User List
     *
     */
	public List<User> getUsers(){
		return getUserDAO().list();
	}

     /**
     * Get User DAO
     *
     * @return userDAO - User DAO
     */
    public UserDao getUserDAO() {
        return userDao;
    }


}
