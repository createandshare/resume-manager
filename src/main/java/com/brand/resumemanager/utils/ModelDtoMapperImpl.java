package com.brand.resumemanager.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.brand.resumemanager.user.User;
import com.brand.resumemanager.user.UserDto;

@Component
public class ModelDtoMapperImpl implements ModelDtoMapper {

	@Override
	public UserDto toUserDto(User user) {
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(user, userDto);
		return userDto;
	}

	@Override
	public User toUser(UserDto userDto) {
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		return user;
	}

}
