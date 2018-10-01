/**
 * 
 */
package com.ern.web.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ern.web.dao.RolesDao;
import com.ern.web.model.Roles;

/**
 * @author sandeep chauhan
 *
 */
@Service("rolesService")
public class RolesService implements Serializable{

	/**
	 * Default Serial Version
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private RolesDao roleDao;
	
	public List<Roles> getRoles(){
		return getRoleDao().list();
	}
	
	public RolesDao getRoleDao() {
		return roleDao;
	}
	
	public void setRoleDao(RolesDao roleDao) {
		this.roleDao = roleDao;
	}

}
