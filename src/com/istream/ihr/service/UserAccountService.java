package com.istream.ihr.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.istream.ihr.orm.UserAccount;

public interface UserAccountService extends UserDetailsService {
	
	public UserAccount getUserAccountByEmployeeId(Integer empId);
	
	public UserAccount getUserAccountByUsername(String username);

}
