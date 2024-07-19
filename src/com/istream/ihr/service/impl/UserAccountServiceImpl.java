/**
 * 
 */
package com.istream.ihr.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.istream.ihr.dao.EmployeeDao;
import com.istream.ihr.dao.UserAccountDao;
import com.istream.ihr.orm.Employee;
import com.istream.ihr.orm.UserAccount;
import com.istream.ihr.service.UserAccountService;
import com.istream.ihr.vo.GrantedRole;
import com.istream.ihr.vo.User;

/**
 * @author Istream2
 *
 */
@Service("userAccountService")
public class UserAccountServiceImpl implements UserAccountService, AuthenticationUserDetailsService {

	@Autowired
	UserAccountDao userAccountDao;
	
	@Autowired
	EmployeeDao employeeDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.istream.ihr.service.UserAccountService#getUserAccountByEmployeeId(
	 * java.lang.Integer)
	 */
	@Override
	public UserAccount getUserAccountByEmployeeId(Integer empId) {
		return userAccountDao.getUserAccountByEmployeeId(empId);
	}

	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		UserAccount userAccount = getUserAccountByUsername(username);
		System.out.println("UserAccount:" + userAccount);
		if (userAccount == null) {
			 throw new UsernameNotFoundException("User does not match");
		}
		return getUserDetails(userAccount);

	}

	public UserAccount getUserAccountByUsername(String username) {
		System.out.println("Before DAO Call:"+username);
		return userAccountDao.getUserAccountByUsername(username);
	}

	private User getUserDetails(UserAccount userAccount) {
		User user = new User();
		user.setUsername(userAccount.getUsername());
		user.setPassword("test");
		user.setEmployeeId(userAccount.getEmployeeID());
		
		Employee emp = employeeDao.getEmployeeDetails(Long.parseLong(""+userAccount.getEmployeeID()));
		user.setFirstName(emp.getFirstName());
		user.setLastName(emp.getLastName());
		
		List<GrantedRole> granedAuthorities = new ArrayList<GrantedRole>();
		GrantedRole grantedRole = new GrantedRole();
		grantedRole.setName(userAccount.getRole().getRoleName());
		granedAuthorities.add(grantedRole);
		
		user.setAuthorities(granedAuthorities);
		return user;
	}

	public UserAccountDao getUserAccountDao() {
		return userAccountDao;
	}

	public void setUserAccountDao(UserAccountDao userAccountDao) {
		this.userAccountDao = userAccountDao;
	}

	@Override
	public UserDetails loadUserDetails(Authentication token) throws UsernameNotFoundException {
		
		if(token != null && token.getPrincipal()!=null && token.getPrincipal() instanceof UserDetails) {
			return (UserDetails) token.getPrincipal();
		}
		
		User userDetails = null;
		String username = null;
		String accessCode = null;

		String[] credentials = (String[]) token.getCredentials();
		System.out.println("Inside loadUserDetails:credentials[0]:" + credentials[0]);
		System.out.println("Inside loadUserDetails:credentials[1]:" + credentials[1]);
		boolean principal = (Boolean) token.getPrincipal();

		if (credentials != null && principal == true) {
			accessCode = credentials[0];
			username = credentials[1];
		}

		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(accessCode)) {
			throw new UsernameNotFoundException("Invalid user - " + username);
		} else {
			userDetails = loadUserByUsername(username);
		}

		System.out.println("UserDetails:" + userDetails);

		if (userDetails == null) {
			throw new UsernameNotFoundException("Invalid user - " + username);
		} else {
			return userDetails;
		}

	}

}
