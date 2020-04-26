package com.univeristyguide.login.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="auth_token")
public class AuthToken {
	
	@Id
	@Column(name="token")
	private String token;
	
	public AuthToken()
	{
		
	}

	public AuthToken(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
