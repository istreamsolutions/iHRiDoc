package com.istream.ihr.vo;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.security.core.userdetails.UserDetails;

public class User  implements UserDetails {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	 private String email;
	 private String firstName;
	 private String lastName;
	 private Integer employeeId;
	    private boolean enabled = true;
	    private String password ="test";
	    private boolean accountNonExpired = true;
	    private boolean accountNonLocked = true;
	    private boolean credentialsNonExpired = true;
	   

	 
	 /* Spring Security related fields*/
	 private List<GrantedRole> authorities;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<GrantedRole> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<GrantedRole> authorities) {
		this.authorities = authorities;
	}
	 
	 
	 public boolean isEnabled() {
	        return enabled;
	    }
	    public void setEnabled(boolean enabled) {
	        this.enabled = enabled;
	    }
	    
	
	    public String getPassword() {
	        return password;
	    }
	    public void setPassword(String password) {
	        this.password = password;
	    }

		public boolean isAccountNonExpired() {
			return accountNonExpired;
		}

		public void setAccountNonExpired(boolean accountNonExpired) {
			this.accountNonExpired = accountNonExpired;
		}

		public boolean isAccountNonLocked() {
			return accountNonLocked;
		}

		public void setAccountNonLocked(boolean accountNonLocked) {
			this.accountNonLocked = accountNonLocked;
		}

		public boolean isCredentialsNonExpired() {
			return credentialsNonExpired;
		}

		public void setCredentialsNonExpired(boolean credentialsNonExpired) {
			this.credentialsNonExpired = credentialsNonExpired;
		}
		
		public Integer getEmployeeId() {
			return employeeId;
		}

		public void setEmployeeId(Integer employeeId) {
			this.employeeId = employeeId;
		}

		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
		}
	
}
