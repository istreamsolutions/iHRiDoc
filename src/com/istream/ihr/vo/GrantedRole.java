package com.istream.ihr.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.security.core.GrantedAuthority;

public class GrantedRole implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return name;
	}
	
	public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
    
    public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
