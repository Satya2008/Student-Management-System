package com.platformcommons.Config.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.platformcommons.Entities.Admin;
import com.platformcommons.Entities.Student;
import com.platformcommons.Repository.AdminRepository;
import com.platformcommons.Repository.StudentRepository;


@Service
public class ManualUserDetailsService implements UserDetailsService {
    
	@Autowired
	 private StudentRepository studentRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	public boolean isAdmin(String email) {
	   Optional<Admin> admin = adminRepository.findByEmail(email);
		if(admin.isPresent()) return true;
		else return false;
	}
	
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		if(isAdmin(email)) {
			Optional<Admin> admin = adminRepository.findByEmail(email);
			 
			 if(admin.isEmpty()) throw new UsernameNotFoundException("Admin not found");
			 Admin us = admin.get();
			 
			List<GrantedAuthority> authorities = new ArrayList<>() ;
			SimpleGrantedAuthority autho = new SimpleGrantedAuthority("ROLE_"+us.getRole()) ;
			authorities.add(autho) ;
			User secUser = new User(us.getEmail(), us.getPassword(),  authorities) ;
			return secUser ;
		}else {
			
			Optional<Student> myUser = studentRepository.findByUniqueStudentCode(email);
				 
				 if(myUser.isEmpty()) throw new UsernameNotFoundException("User not found");
				 Student us = myUser.get();
				 
				List<GrantedAuthority> authorities = new ArrayList<>() ;
				SimpleGrantedAuthority autho = new SimpleGrantedAuthority("ROLE_"+us.getRole()) ;
				authorities.add(autho) ;
				User secUser = new User(us.getUniqueStudentCode(), us.getPassword(),  authorities) ;
				return secUser ;

				
			}
		}


}
