package com.univeristyguide.login.dto;

import java.util.Date;



public class UserDto {
	

	private int id;
	

	private String firstName;
	
	
	private String lastName;
	

	private String email;
	

	private String password;
	

	private Date createdDate;
	
	private Date lastModifiedDate;
	
	private String university;
	

	private String roles;

	
	
	
	
	public UserDto() {
	
	}


	


	public UserDto(int id, String firstName, String lastName, String email, String password, Date createdDate,
			Date lastModifiedDate, String university, String roles) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
		this.university = university;
		this.roles = roles;
	}





	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	


	public String getUniversity() {
		return university;
	}


	public void setUniversity(String university) {
		this.university = university;
	}


	public String getRoles() {
		return roles;
	}


	public void setRoles(String roles) {
		this.roles = roles;
	}


	@Override
	public String toString() {
		return "UserDto [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", createdDate=" 
				+ ", university=" + university + ", roles=" + roles + "]";
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}


	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
	

}
