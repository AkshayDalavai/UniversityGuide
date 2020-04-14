package com.univeristyguide.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.univeristyguide.login.dao.UserDAO;
import com.univeristyguide.login.entity.User;

@Service
public class UserServiceImpl implements UserService{
	
	private UserDAO userDao;
	
	@Autowired
	public UserServiceImpl(UserDAO theUserDAO)
	{
		userDao =theUserDAO;
	}
	
	@Override
	@Transactional
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}

}
