/**
 * 
 */
package com.ern.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Sandeep
 *
 */
@Entity
@Table(name = "T_ROLES")
public class Roles {

	@Id
	@Column(name = "ROLE_CODE")
	private String roleCode;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
