package com.univeristyguide.login.service;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.univeristyguide.login.dto.UserDto;
import com.univeristyguide.login.dto.dtoconverter.ToDtoConverter;
import com.univeristyguide.login.entity.Role;
import com.univeristyguide.login.entity.User;
import com.univeristyguide.login.repository.RoleRepository;
import com.univeristyguide.login.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
	
	private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	private UserRepository userRepository;
	
	public UserService() {
		
		
	}
	
	@Autowired
	public UserService(UserRepository theUserRepository,RoleRepository theRoleRepository,
			BCryptPasswordEncoder theBCryptPasswordEncoder)
	{
		userRepository = theUserRepository;
		roleRepository = theRoleRepository;
		bCryptPasswordEncoder = theBCryptPasswordEncoder;
		
	}
	
	public void signUp(final User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findRoleByName("USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        this.userRepository.save(user);
    }

	/*public void saveUser(User user)
	{
		
		userRepository.save(user);
	}*/
	
	public UserDto updateUser(User user)
	{
		Optional<User> result = userRepository.findById(user.getId());
		
		User theUser = null;
		if(result.isPresent())
		{
			theUser=result.get();
		}
		else
		{
			throw new RuntimeException("Could'nt update as the user id - " + user.getId()+" not present");
		}

		user.setCreatedDate(theUser.getCreatedDate());
		//user.setCreatedBy(theUser.getCreatedBy());
		user.setLastModifiedDate(theUser.getLastModifiedDate());
		//user.setLastModifiedBy(theUser.getLastModifiedBy());
		
		
		userRepository.save(user);
		return ToDtoConverter.userToDtoConverter(user);
	}
	
	public List<UserDto> findAll() {
		List<User> users = userRepository.findAll();
		return users.stream().map(ToDtoConverter::userToDtoConverter).collect(Collectors.toList());
	}
	
	@PreAuthorize("hasRole('USER')")
	public UserDto findUserById(int theId)
	{
		Optional<User> result = userRepository.findById(theId);
		
		User theUser = null;
		if(result.isPresent())
		{
			theUser=result.get();
		}
		else
		{
			throw new RuntimeException("Did not find user id - " + theId);
		}
		
		return ToDtoConverter.userToDtoConverter(theUser);
	}
	
	 public User findOne(final String username) {
	        return userRepository.findByEmail(username);
	    }

	    @Override
	    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
	        User user = userRepository.findByEmail(username);
	        if (user == null) {
	            throw new UsernameNotFoundException("Invalid username or password.");
	        }
	        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
	    }

	    private Set<SimpleGrantedAuthority> getAuthority(final User user) {
	        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
	        user.getRoles().forEach(role -> {
	            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
	        });
	        return authorities;
	    }

	
}
