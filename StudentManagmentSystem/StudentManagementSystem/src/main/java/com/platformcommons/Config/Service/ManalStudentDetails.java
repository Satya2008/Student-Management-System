package com.platformcommons.Config.Service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.platformcommons.Entities.Student;

public class ManalStudentDetails implements UserDetails {
	
	private Student student;
	

	public ManalStudentDetails(Student student) {
		super();
		this.student = student;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList();
	}

	@Override
	public String getPassword() {
		return student.getPassword();
	}

	@Override
	public String getUsername() {
		return student.getUniqueStudentCode();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
	
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
