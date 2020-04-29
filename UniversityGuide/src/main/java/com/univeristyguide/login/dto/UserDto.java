package com.univeristyguide.login.dto;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.univeristyguide.login.entity.Role;



public class UserDto {
	

	private int id;
	

	private String firstName;
	
	
	private String lastName;
	

	private String email;
	

	private String password;
	

	private Date createdDate;
	
	private Date lastModifiedDate;
	
	private String university;
	

	private Set<Role> roles;

	
	public UserDto() {
	
	}


	public UserDto(int id, String firstName, String lastName, String email, String password, Date createdDate,
			Date lastModifiedDate, String university, Set<Role> roles) {
		super();
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

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonSetter
	public void setPassword(String password) {
		this.password = password;
	}

	@JsonIgnore
	public Date getCreatedDate() {
		return createdDate;
	}

	@JsonSetter
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@JsonIgnore
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	@JsonSetter
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}


	public String getUniversity() {
		return university;
	}


	public void setUniversity(String university) {
		this.university = university;
	}


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	@Override
	public String toString() {
		return "UserDto [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", createdDate=" + createdDate + ", lastModifiedDate=" + lastModifiedDate
				+ ", university=" + university + ", roles=" + roles + "]";
	}

}
