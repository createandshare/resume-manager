package com.brand.resumemanager.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/users")	
@Slf4j
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("")
	public ResponseEntity<UserCollectionDto> getUsers(){
		log.debug("Getting users");
		return ResponseEntity.ok(userService.getUsers());
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
		log.debug("Getting user with id: "+id);
		return ResponseEntity.of(userService.getUserById(id));
	}
	
	@PostMapping("")
	public ResponseEntity<Object> createUser(@RequestBody UserDto userDto) {
		log.debug("Saving user: "+userDto);
		Integer id = userService.createUser(userDto);
		URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(id)
					.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable Integer id) {
		log.debug("Deleting user with id: "+id);
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
}
