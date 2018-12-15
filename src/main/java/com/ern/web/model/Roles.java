package com.ern.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
/*
 * author Sandeep
 * This model class will be used for maintaining role master
 */

@Entity
@Table(name = "T_ROLES")
public class Roles {

	@Id
	@GeneratedValue
	@Column(name = "ROLE_ID")
	private long roleId;
	
	@Column(name = "ROLE_CODE")
	@Size(max = 10, min = 3, message = "{role.code.invalid}")
	private String roleCode;
	 
    @Column(name = "ROLE_DESC")
    private String roleDesc;

/**
 * @return the roleId
 */

public long getRoleId() {
	return roleId;
}

/**
 * @param roleId the roleId to set
 */
public void setRoleId(long roleId) {
	this.roleId = roleId;
}

/**
 * @return the roleCode
 */
public String getRoleCode() {
	return roleCode;
}

/**
 * @param roleCode the roleCode to set
 */
public void setRoleCode(String roleCode) {
	this.roleCode = roleCode;
}

/**
 * @return the roleDesc
 */
public String getRoleDesc() {
	return roleDesc;
}

/**
 * @param roleDesc the roleDesc to set
 */
public void setRoleDesc(String roleDesc) {
	this.roleDesc = roleDesc;
}

}
