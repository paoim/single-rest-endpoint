package com.exercise.singlerestendpoint;

import static com.exercise.singlerestendpoint.constant.Path.USER_BASE_URL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.exercise.singlerestendpoint.controller.UserController;
import com.exercise.singlerestendpoint.model.User;
import com.exercise.singlerestendpoint.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private UserService userService;

	@Test
	public void testMockMvcIsNotNull() throws Exception {
		assertThat(mockMvc).isNotNull();
	}

	@Test
	public void testUserServiceIsNotNull() throws Exception {
		assertThat(userService).isNotNull();
	}

	@Test
	public void testGettingOneUser() throws Exception {
		User user = new User();
		when(userService.getUser(1)).thenReturn(user);
		mockMvc.perform(get(USER_BASE_URL + "/1"))
			.andDo(print())
			.andExpect(status().isMethodNotAllowed());
	}

	@Test
	public void testGettingAllUsers() throws Exception {
		List<User> users = new ArrayList<>();
		when(userService.getUsers()).thenReturn(users);
		mockMvc.perform(get(USER_BASE_URL))
			.andDo(print())
			.andExpect(status().isNotFound());
	}
	
	@Test
	public void testUpdatingUserWithEmptyPathParam() throws Exception {
		User user = new User("Pao Im", "paoim@yahoo.com");
		mockMvc.perform(put(USER_BASE_URL)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8")
				.content(objectMapper.writeValueAsBytes(user)))
			.andDo(print())
			.andExpect(status().isNotFound());
	}
	
	@Test
	public void testUpdatingUserWithNullPathParam() throws Exception {
		User user = new User("Pao Im", "paoim@yahoo.com");
		mockMvc.perform(put(USER_BASE_URL + "/null")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8")
				.content(objectMapper.writeValueAsBytes(user)))
			.andDo(print())
			.andExpect(status().isBadRequest());
	}
	
	@Test
	public void testUpdatingUserWithMinusOnePathParam() throws Exception {
		User user = new User("Pao Im", "paoim@yahoo.com");
		mockMvc.perform(put(USER_BASE_URL + "/-1")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8")
				.content(objectMapper.writeValueAsBytes(user)))
			.andDo(print())
			.andExpect(status().isNotFound());
	}

	@Test
	public void testUpdatingUserWithInvalidPayload() throws Exception {
		User user = new User();
		mockMvc.perform(put(USER_BASE_URL + "/1")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8")
				.content(objectMapper.writeValueAsBytes(user)))
			.andDo(print())
			.andExpect(status().isBadRequest());
	}

	@Test
	public void testUpdatingUserWithInvalidName() throws Exception {
		User user = new User();
		user.setEmail("paoim@yahoo.com");
		mockMvc.perform(put(USER_BASE_URL + "/1")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8")
				.content(objectMapper.writeValueAsBytes(user)))
			.andDo(print())
			.andExpect(status().isBadRequest());
	}

	@Test
	public void testUpdatingUserWithInvalidEmail() throws Exception {
		User user = new User("Pao Im", "paoim");
		mockMvc.perform(put(USER_BASE_URL + "/1")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8")
				.content(objectMapper.writeValueAsBytes(user)))
			.andDo(print())
			.andExpect(status().isBadRequest());
	}

	@Test
	public void testUpdatingUserWithValidEmail() throws Exception {
		User user = new User("Pao Im", "paoim@yahoo.com");
		mockMvc.perform(put(USER_BASE_URL + "/1")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8")
				.content(objectMapper.writeValueAsBytes(user)))
			.andDo(print())
			.andExpect(status().isNotFound());
	}
}
