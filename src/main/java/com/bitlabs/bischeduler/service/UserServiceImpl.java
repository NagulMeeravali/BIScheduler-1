package com.bitlabs.bischeduler.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bitlabs.bischeduler.entity.UpdatePassword;
import com.bitlabs.bischeduler.entity.Users;
import com.bitlabs.bischeduler.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService  {

	@Autowired
    private UserRepository userrepository;
	
	 PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	
	
	@Override
	public boolean saveUserDetails(Users users) {
         boolean b=false;
        
         
         List<Users> user=userrepository.findAll();
		for(Users u:user) {
		
		if(u.getUsername().equals(users.getUsername())) {
			
		b=false;
		break;
		}
		else {
			b=true;
		}	
	}
		if(b==true) {
	
			
		String pwd=passwordEncoder.encode(users.getPassword());
		
       users.setPassword(pwd);
		userrepository.save(users);}
		return b;
	}
		
	@Override
	public List<Users> getAllUsersDetails() {
		// TODO Auto-generated method stub
		return userrepository.findAll();
	}
	@Override
	public Users getUserById(int id) {
		// TODO Auto-generated method stub
		return userrepository.findById(id).get();
	}
	@Override
	public void deleteUserById(int id) {
		// TODO Auto-generated method stub
		userrepository.deleteById(id);
	}
	@Override
	public List<Users> getUserByUserName(String uname) {
		
		return userrepository.findUserByUsername(uname);
	}
	@Override
	public String updatePassword(UpdatePassword updpwd) {
		// TODO Auto-generated method stub
		
	Users u=userrepository.findByUsername(updpwd.getUsername());//getting from database
	
	if(u.getPassword().equals(updpwd.getOldpwd()))
	{
	u.setPassword(updpwd.getConfirmpwd());
	
	userrepository.save(u);
	
	return "new password is updated successfully";
	}
	else {
		return "old password is incorrect";
	}
				
	}

	@Override
	public boolean login(Users user) {
		boolean b=false;
		List<Users> users=userrepository.findAll();
		for(Users u:users) {
			if(u.getUsername().equals(user.getUsername()) && passwordEncoder.matches(user.getPassword(),u.getPassword()) ){
				b=true;
			}
		}
		return b;
	}
	  @Override
	    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
	        
	    	Users users =userrepository.findByUsername(s);
	    	
	    	
	    	//ArrayList al=new ArrayList();
	    	//al.add(users.getFull_name());
	    	
	    	
	    	return new User(users.getUsername(), users.getPassword(),Arrays.stream(users.getRoles().split(","))
					.map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList()));
	    }
	

}
