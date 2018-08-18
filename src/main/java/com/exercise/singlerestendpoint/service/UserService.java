package com.exercise.singlerestendpoint.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exercise.singlerestendpoint.model.User;
import com.exercise.singlerestendpoint.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> getUsers() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}
	
	public User getUser(long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return user.get();
		}
		return null;
	}
	
	public User createUser(User user) {
		User userCreated = userRepository.save(user);
		return userCreated;
	}
	
	public User updateUser(long id, User user) {
		User updatedUser = null;
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent() && null != user) {
			boolean isUpdatedUser = false;
			User userNeedToUpdate = userOptional.get();
			if (!userNeedToUpdate.getName().equalsIgnoreCase(user.getName())) {
				userNeedToUpdate.setName(user.getName());
				isUpdatedUser = true;
			}
			if (!userNeedToUpdate.getEmail().equalsIgnoreCase(user.getEmail())) {
				userNeedToUpdate.setEmail(user.getEmail());
				isUpdatedUser = true;
			}
			if (isUpdatedUser) {
				updatedUser = userRepository.save(userNeedToUpdate);
			}
		}
		return updatedUser;
	}
	
	public boolean deleteUserById(long id) {
		if (-1 != id) {
			userRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
