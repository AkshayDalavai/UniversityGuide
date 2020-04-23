package com.univeristyguide.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.univeristyguide.login.entity.ConfirmationToken;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, String> {

	ConfirmationToken findByConfirmationToken(String confirmationToken);
	
}
