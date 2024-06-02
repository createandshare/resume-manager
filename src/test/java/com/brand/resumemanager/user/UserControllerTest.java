package com.brand.resumemanager.user;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserController.class)
@TestInstance(Lifecycle.PER_CLASS)
@WithMockUser
@DisplayName("When testing Users resource")
public class UserControllerTest {

	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper objectMapper;
	@MockBean
	UserService userService;
	
	String BASE_ENDPOINT;
	UserDto USER1;
	UserDto USER2;
	UserDto USER3;
	
	@BeforeAll
	void init() {
		BASE_ENDPOINT = "/v1/users";
		this.USER1 = new UserDto(1,"username1","user","name1","email1@gmail.com","1234567890",true);
		this.USER2 = new UserDto(2,"username2","user","name2","email2@gmail.com","2234567890",true);
		this.USER3 = new UserDto(3,"username3","user","name3","email3@gmail.com","3234567890",true);
	}
	
	@Nested
	@DisplayName("GET /users")
	class TestGetUsers{
		@Test
		@DisplayName("should return a list of users with valid data")
		void getUsersTest_success() throws Exception {
			UserCollectionDto userCollection = new UserCollectionDto();
			userCollection.setUsers(Arrays.asList(USER1,USER2,USER3));
			
			Mockito.when(userService.getUsers()).thenReturn(userCollection);
			
			mockMvc.perform(
					get(BASE_ENDPOINT).accept(MediaType.APPLICATION_JSON)
			).andExpect(status().isOk())
			.andExpect(jsonPath("$.users[0].userName", is("username1")))
			.andExpect(jsonPath("$.users[1].userName", is("username2")))
			.andExpect(jsonPath("$.users[2].userName", is("username3")));
		}
		
		@Test
		@DisplayName("should return an empty JSON array if no records present")
		@Disabled//TODO - remove this annotation when implemented
		void getUsersTest_when_no_records() {
			fail("to be implemented");
		}
	}
	
	@Nested
	@DisplayName("POST /users")
	class TestPostUsers{
		@Test
		@DisplayName("should create a new user and return its location")
		void postUsersTest_success() throws Exception {
			UserDto userDto = USER1;
			
			Mockito.when(userService.createUser(userDto)).thenReturn(USER1.getId());
			
			mockMvc.perform(
					post(BASE_ENDPOINT)
					.contentType(MediaType.APPLICATION_JSON)
					.with(csrf())
					.content(objectMapper.writeValueAsString(userDto))
			).andExpect(status().isCreated())
			.andReturn().getResponse()
			.getHeaderValue("Location").toString()
			.endsWith(String.valueOf(USER1.getId()));
		}
		
		@Test
		@DisplayName("should return a json object with useful error message, if record exists")
		@Disabled//TODO - remove this annotation when implemented
		void postUsersTest_when_record_exists() {
			fail("to be implemented");
		}
		
		@Test
		@DisplayName("should return a json object with error message, if any other error")
		@Disabled//TODO - remove this annotation when implemented
		void postUsersTest_when_generic_error() {
			fail("to be implemented");
		}
	}
	
	@Nested
	@DisplayName("DELETE /users")
	class TestDeleteUsers{
		@Test
		@DisplayName("should delete the user and return no content")
		void deleteUsersTest_success() throws Exception {
						
			mockMvc.perform(
					delete(BASE_ENDPOINT+"/"+USER1.getId())
					.with(csrf())
			).andExpect(status().isNoContent());
		}
		
		@Test
		@DisplayName("should return a json object with useful error message, if no record")
		@Disabled//TODO - remove this annotation when implemented
		void deleteUsersTest_when_no_record() {
			fail("to be implemented");
		}
		
		@Test
		@DisplayName("should return a json object with error message, if any other error")
		@Disabled//TODO - remove this annotation when implemented
		void deleteUsersTest_when_generic_error() {
			fail("to be implemented");
		}
	}
}
