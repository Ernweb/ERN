package com.ern.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.springframework.stereotype.Component;

import com.ern.web.model.Roles;
import com.ern.web.service.UserService;

@Component("userConverter")
public class UserConverter implements Converter{
	private UserService userService;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Roles roles = getUserService().getRolebyCode(value);
		return roles;
	}
	
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return ((Roles) value).getRoleCode();
	}
	/**
	 * @return the userService
	 */
	public UserService getUserService() {
		return userService;
	}
	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
