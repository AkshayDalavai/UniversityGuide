package com.univeristyguide.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.univeristyguide.login.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	
}
