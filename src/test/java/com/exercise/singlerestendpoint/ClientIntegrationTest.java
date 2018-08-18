package com.exercise.singlerestendpoint;

import static com.exercise.singlerestendpoint.constant.Path.USER_BASE_URL;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.exercise.singlerestendpoint.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ClientIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testGettingOneUserWithResultGetNotSupport() throws Exception {
		ResponseEntity<User> responseEntity = restTemplate.getForEntity(USER_BASE_URL + "/1", User.class);
		assertTrue("Response Status", responseEntity.getStatusCode() == HttpStatus.METHOD_NOT_ALLOWED);
	}

	@Test
	public void testGettingAllUsersWithResultNotFound() throws Exception {
		ResponseEntity<User> responseEntity = restTemplate.getForEntity(USER_BASE_URL, User.class);
		assertTrue("Response Status", responseEntity.getStatusCode() == HttpStatus.NOT_FOUND);
	}

	@Test
	public void testCreatingUserWithResultNotFound() throws Exception {
		ResponseEntity<User> responseEntity = restTemplate.postForEntity(USER_BASE_URL, new User("Foo", "foo@gmail.com"), User.class);
		assertTrue("Response Status", responseEntity.getStatusCode() == HttpStatus.NOT_FOUND);
	}

	@Test
	public void testUpdatingUserWithInvalidPayloadAndResultBadRequest() throws Exception {
		ResponseEntity<User> responseEntity = getResponseEntityFromUpdatingFirstUser(new User());
		assertTrue("Response Status", responseEntity.getStatusCode() == HttpStatus.BAD_REQUEST);
	}

	@Test
	public void testUpdatingUserWithInvalidEmailAndResultBadRequest() throws Exception {
		ResponseEntity<User> responseEntity = getResponseEntityFromUpdatingFirstUser(new User("Pao Im", "paoim"));
		assertTrue("Response Status", responseEntity.getStatusCode() == HttpStatus.BAD_REQUEST);
	}

	@Test
	public void testUpdatingUserWithValidPayloadAndResultNotFound() throws Exception {
		ResponseEntity<User> responseEntity = getResponseEntityFromUpdatingFirstUser(new User("Pao Im", "paoim@yahoo.com"));
		assertTrue("Response Status", responseEntity.getStatusCode() == HttpStatus.NOT_FOUND);
	}
	
	private ResponseEntity<User> getResponseEntityFromUpdatingFirstUser(User user) throws Exception {
		Map<String, String> param = new HashMap<String, String>();
		param.put("id", "1");
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<User> requestEntity = new HttpEntity<User>(user, headers);
		ResponseEntity<User> responseEntity = restTemplate.exchange(USER_BASE_URL + "/{id}", HttpMethod.PUT, requestEntity, User.class, param);
		return responseEntity;
	}
}
