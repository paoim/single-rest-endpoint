package com.exercise.singlerestendpoint.controller;

import static com.exercise.singlerestendpoint.constant.Path.USER_BASE_URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exercise.singlerestendpoint.model.User;
import com.exercise.singlerestendpoint.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(USER_BASE_URL)
@Api(value = USER_BASE_URL)
public class UserController {
	
	@Autowired
	private UserService userService;

	@PutMapping("{id}")
	@ApiOperation(value = "Update User by UserId and User JSON (name and email)")
	public ResponseEntity<User> updateUser(
			@PathVariable("id") long id,
			@Validated @RequestBody User user) {
		User updatedUser = userService.updateUser(id, user);
		if (null != updatedUser) {
			return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
		}
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}
}
