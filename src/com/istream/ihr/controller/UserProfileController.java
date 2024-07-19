package com.istream.ihr.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.istream.ihr.vo.UserProfileWrapper;
import com.istream.ihr.vo.User;

@RestController
public class UserProfileController {

    @RequestMapping(value = "/userProfile", method = RequestMethod.GET, headers="Accept=application/json")
	public ResponseEntity<UserProfileWrapper> getUserProfile() throws Exception {		
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("Inside UserProfileController:"+user);
		return new ResponseEntity<UserProfileWrapper>(transformUserProfile(user),HttpStatus.OK);
	}
    
    private UserProfileWrapper transformUserProfile(User user) {
    	UserProfileWrapper userProfileWrapper = new UserProfileWrapper ();
    	userProfileWrapper.setEmployeeId(user.getEmployeeId());
    	userProfileWrapper.setFirstName(user.getFirstName());
    	userProfileWrapper.setLastName(user.getLastName());
    	userProfileWrapper.setRole(user.getAuthorities().get(0).getName());
    	return userProfileWrapper;
    }
	
}
