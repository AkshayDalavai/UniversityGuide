package com.univeristyguide.login.dto;

public class AuthTokenDto {
	
	private String token;
	
	private UserDto user;
	
	public AuthTokenDto() {
		
	}

	public AuthTokenDto(String token, UserDto user) {
		this.token = token;
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "AuthTokenDto [token=" + token + ", user=" + user + "]";
	}
	
}
