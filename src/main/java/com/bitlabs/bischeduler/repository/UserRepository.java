package com.bitlabs.bischeduler.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bitlabs.bischeduler.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

	List<Users> findUserByUsername(String username);
	 public Users findByUsername(String username);
}
