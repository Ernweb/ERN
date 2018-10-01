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

import com.ern.web.model.Roles;
import com.ern.web.service.RolesService;

/**
 * @author sandeep chauhan
 *
 */
@ManagedBean(name="roleMB")
@RequestScoped
public class RolesBean implements Serializable{

	private static final long serialVersionUID = 1L;
	List<Roles> roles;
	/**
	* Spring User Service is injected...
	*/
	@ManagedProperty(value="#{rolesService}")
	private RolesService roleService;
	
	public List<Roles> getRoles(){
		ArrayList<Roles> roleList = new ArrayList<Roles>();
		roleList.addAll(getRoleService().getRoles());
		return roleList;
	}

	/**
     * Get Role Service
     *
     * @return IRoleService - Role Service
     */
    public RolesService getRoleService() {
        return roleService;
    }
    
    /**
     * Set Role Service
     *
     * @param Role IRoleService - Role Service
     */
    public void setRoleService(RolesService roleService) {
        this.roleService = roleService;
    }

}
