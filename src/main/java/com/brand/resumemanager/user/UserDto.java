package com.brand.resumemanager.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private Integer id;
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private boolean active;
}
