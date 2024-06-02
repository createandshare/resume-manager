package com.brand.resumemanager.user;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brand.resumemanager.utils.ModelDtoMapper;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	UserCollectionDto userCollection;
	@Autowired
	ModelDtoMapper CONVERT;
	
	public UserCollectionDto getUsers() {
		Iterable<User> users = userRepository.findAll();
		
		userCollection.setUsers(
			StreamSupport.stream(users.spliterator(), false)
			.map(CONVERT::toUserDto)
			.collect(Collectors.toList())
		);	
		return userCollection;
	}
	
	public Optional<UserDto> getUserById(Integer id) {
		Optional<User> user = userRepository.findById(id);
		Optional<UserDto> result;
		if(user.isEmpty()) {
			result = Optional.empty();
		}		
		result = Optional.of(
					CONVERT.toUserDto(user.get())
				);
		return result;
	}
	
	public Integer createUser(UserDto userDto){
		User savedUser = userRepository.save(CONVERT.toUser(userDto));
		
		return savedUser.getId();
	}
	
	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
	}
}
