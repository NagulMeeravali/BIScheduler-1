package com.bitlabs.bischeduler.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitlabs.bischeduler.entity.AuthenticationResponse;
import com.bitlabs.bischeduler.entity.Jobs;
import com.bitlabs.bischeduler.entity.UpdatePassword;
import com.bitlabs.bischeduler.entity.Users;
import com.bitlabs.bischeduler.filters.JwtRequestFilter;
import com.bitlabs.bischeduler.reponse.ResponseHandler;
import com.bitlabs.bischeduler.service.JobsServiceInterface;
import com.bitlabs.bischeduler.service.JwtUtil;
import com.bitlabs.bischeduler.service.UserService;

@CrossOrigin
@RestController
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
   @Autowired
	private JwtUtil jwtTokenUtil;

	 	
	@Autowired
	UserService userservice;
	
	@PostMapping("/userRegistration")
	public ResponseEntity<Object> register(@Valid @RequestBody Users users) {
		boolean b=userservice.saveUserDetails(users);
		if(b==true) {
		return ResponseHandler.generateResponse("Registered successfully", HttpStatus.OK, b);
		}
		else {
			return ResponseHandler.generateResponse("Entered Username is already existed", HttpStatus.BAD_REQUEST, b);
		}
	}
	@GetMapping("/getAllUsersDetails")
	public List<Users> getAllUsers()
	{
		boolean b=false;
		List<Users> user=userservice.getAllUsersDetails();
		if (user==null) {
			return (List<Users>) ResponseHandler.generateResponse("User data is empty", HttpStatus.OK, b);
		}
		else {
			return userservice.getAllUsersDetails();
		}
	
	}
	@GetMapping("/getUserById")
	public Users getUserById(@RequestBody Users user)
	{
		return userservice.getUserById(user.getId());
	}
	
	@DeleteMapping("/deleteUserById")
	public String deleteUserById(@RequestBody Users user)
	{
		userservice.deleteUserById(user.getId());
		return "deleted successfully";
	}
  
	
	@GetMapping("/getUserByUserName")
	public List<Users> getUserByName(@RequestBody Users user){
		return userservice.getUserByUserName(user.getUsername());
	}
	
	
	@PutMapping("/updatePassword")
	public String updatePassword(@RequestBody UpdatePassword updpwd)
	{
		
		if(updpwd.getNewpwd().equals(updpwd.getConfirmpwd())) {
			return userservice.updatePassword(updpwd);
			
			
		}
		else
		{
			return "new password and confirm password both should be same";
		}
		
		
	}
    @PostMapping("/login1")
	public boolean login1(@RequestBody Users user) {
		
    	return true;
	}
	
	/*
    @PostMapping("/authenticate")
	public ResponseEntity<Object> login(@RequestBody Users user) throws Exception {
				
		boolean b= userservice.login(user);
		if(b==true) {
			return createAuthenticationToken(user);
		}
		else {
			return ResponseHandler.generateResponse("Please enter correct username and password", HttpStatus.BAD_REQUEST, b);
		}
	}
	
	public ResponseEntity<Object> createAuthenticationToken(@RequestBody Users user) throws Exception {
		
		 
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
  
		final UserDetails userDetails = userservice.loadUserByUsername(user.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		//return ResponseEntity.ok(new AuthenticationResponse(jwt));
		return ResponseHandler.generateResponse("Login successfully"+userDetails.getAuthorities(), HttpStatus.OK, new AuthenticationResponse(jwt));
		  
	}
	*/
	

	}
	

	





