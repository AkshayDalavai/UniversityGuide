package com.univeristyguide.login.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.univeristyguide.login.dto.UserDto;
import com.univeristyguide.login.dto.dtoconverter.ToDtoConverter;

import com.univeristyguide.login.entity.User;
import com.univeristyguide.login.repository.UserRepository;

@Service
public class UserService {
	

	private UserRepository userRepository;
	
	public UserService() {
		
		
	}
	
	@Autowired
	public UserService(UserRepository theUserRepository)
	{
		userRepository = theUserRepository;
	}
	
	
	


	public void saveUser(User user)
	{
		
		userRepository.save(user);
	}
	
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
		user.setCreatedBy(theUser.getCreatedBy());
		user.setLastModifiedDate(theUser.getLastModifiedDate());
		user.setLastModifiedBy(theUser.getLastModifiedBy());
		
		
		userRepository.save(user);
		return ToDtoConverter.userToDtoConverter(user);
	}
	
	public List<UserDto> findAll() {
		List<User> users = userRepository.findAll();
		return users.stream().map(ToDtoConverter::userToDtoConverter).collect(Collectors.toList());
	}
	
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
	
	
}
