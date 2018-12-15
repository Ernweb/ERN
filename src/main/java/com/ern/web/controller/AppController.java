package com.ern.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ern.web.model.User;
import com.ern.web.service.UserService;


@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
    UserService userService;
	/**
     * This method handles login request.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
    	return "login";
    }
	/**
     * This method will redirect to index.
     */
    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
 
        List <User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "index";
    }
	
    /**
     * This method will redirect to addUser.
     */
    @RequestMapping(value = { "/", "/addUser" }, method = RequestMethod.GET)
    public String addUsers() {
 
        return "addUser";
    }
    /**
     * This method handles Access-Denied redirect.
     */
    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(Model model) {
    	model.addAttribute("loggedinuser", getPrincipal());
        model.addAttribute("message", "Access Denied");
        return "accessDenied";
    }
    
    /**
     * This method returns the principal[user-name] of logged-in user.
     */
    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}