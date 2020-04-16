package com.univeristyguide.login.dto.dtoconverter;

import com.univeristyguide.login.dto.UserDto;
import com.univeristyguide.login.entity.User;

public class ToDtoConverter {

	public static UserDto userToDtoConverter(final User user)
	{
		UserDto resultUser = new UserDto(user.getId(),
				user.getFirstName(),
				user.getLastName(),
				user.getEmail(),
				user.getPassword(),
				user.getCreatedDate(),
				user.getLastModifiedDate(),
				user.getUniversity(),
				user.getRoles()
				);
		return resultUser;
	}
}
