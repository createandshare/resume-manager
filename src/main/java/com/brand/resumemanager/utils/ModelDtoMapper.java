package com.brand.resumemanager.utils;

import com.brand.resumemanager.user.User;
import com.brand.resumemanager.user.UserDto;

public interface ModelDtoMapper {

	//TODO - Add library like <a href="https://mapstruct.org">Mapstruct</a> to handle mappings automatically
	
	UserDto toUserDto(User user);
	User toUser(UserDto userDto);
}
