package com.brand.resumemanager.user;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class UserCollectionDto {

	List<UserDto> users;
}
