/**
 * 
 */
package com.ern.web.dao;

import java.util.List;

import com.ern.web.model.User;

/**
 * @author Sandeep
 *
 */
public interface UserDao {
	List<User> list();
	User findUserByUsername(String username);
	void addUser(User user);
}
