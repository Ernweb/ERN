/**
 * 
 */
package com.ern.web.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
/**
 * @author Sandeep
 *
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

 
@Entity
@Table(name = "T_USERS")
public class User {
 
   @Id
   @Column(name = "USER_ID")
   private String id;
 
   @Column(name = "USER_NAME")
   @Size(max = 20, min = 3, message = "{user.name.invalid}")
   private String name;
 
   @Column(name = "USER_PASSWORD")
   private String password;
   
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
   private Set<User_Roles> roles = new HashSet<>();


public String getId() {
      return id;
   }
 
   public void setId(String id) {
      this.id = id;
   }
 
   public String getName() {
      return name;
   }
 
   public void setName(String name) {
      this.name = name;
   }
 
   public String getPassword() {
		return password;
   }
	
   public void setPassword(String password) {
		this.password = password;
   }

	public Set<User_Roles> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<User_Roles> userSelectedRole) {
		this.roles = userSelectedRole;
	}

	
  
}