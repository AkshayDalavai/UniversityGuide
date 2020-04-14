package com.univeristyguide.login.dao;

import java.util.List;

import com.univeristyguide.login.entity.User;

public interface UserDAO {
	public List<User> findAll();
}
