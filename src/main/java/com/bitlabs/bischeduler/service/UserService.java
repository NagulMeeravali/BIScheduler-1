package com.bitlabs.bischeduler.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.bitlabs.bischeduler.entity.UpdatePassword;
import com.bitlabs.bischeduler.entity.Users;

public interface UserService {

	boolean saveUserDetails(Users users);

	boolean login(Users user);

	UserDetails loadUserByUsername(String username);
	List<Users> getAllUsersDetails();

	Users getUserById(int id);

	void deleteUserById(int id);

     List<Users> getUserByUserName(String uname);

	String updatePassword(UpdatePassword updpwd);

}
