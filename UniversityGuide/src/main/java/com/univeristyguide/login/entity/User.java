package com.univeristyguide.login.entity;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="users")
public class User extends Auditable<String>{


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	@Column(name="email")
	private String email;

	@Column(name="password")
	private String password;


	@Column(name="university")
	private String university;

	/*
	 * @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	 * 
	 * @JoinTable(name = "user_role" , joinColumns = @JoinColumn(name= "role_id"))
	 * private Set<Role> roles;
	 */
	@Column(name="roles")
	private String roles;
	//private boolean is_enabled;

	public User() {

	}


	/*
	 * public User(int id, String firstName, String lastName, String email, String
	 * password, String university, Set<Role> roles) { super(); this.id = id;
	 * this.firstName = firstName; this.lastName = lastName; this.email = email;
	 * this.password = password; this.university = university; this.roles = roles; }
	 */

	
	

	public int getId() {
		return id;
	}



	public User(int id, String firstName, String lastName, String email, String password, String university,
			String roles) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.university = university;
		this.roles = roles;
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


	/*
	 * public Set<Role> getRoles() { return roles; }
	 * 
	 * 
	 * public void setRoles(Set<Role> roles) { this.roles = roles; }
	 */

	
	
	

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", university=" + university + ", roles=" + roles + "]";
	}


	public String getRoles() {
		return roles;
	}


	public void setRoles(String roles) {
		this.roles = roles;
	}


	



	/*public boolean getIs_enabled() {
		return is_enabled;
	}



	public void setIs_enabled(boolean is_enabled) {
		this.is_enabled = is_enabled;
	}*/


}
