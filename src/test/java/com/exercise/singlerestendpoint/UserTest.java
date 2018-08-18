package com.exercise.singlerestendpoint;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.exercise.singlerestendpoint.model.User;

public class UserTest {
	
	@Test
	public void testTwoEqualIdenticalUsersWithDefaultConstructor() {
		User firstUser = new User();
		User secondUser = new User();
		assertFalse("Compare Two Users by using == operator with fail", firstUser == secondUser);
	}
	
	@Test
	public void testTwoEqualIdenticalUsersWithFail() {
		User firstUser = new User();
		User secondUser = new User();
		firstUser.setName("Pao Im");
		secondUser.setName("MsKesson");
		assertFalse("Compare Two Users by using == operator with fail", firstUser == secondUser);
	}
	
	@Test
	public void testTwoEqualUsersWithDefaultConstructor() {
		User firstUser = new User();
		User secondUser = new User();
		assertFalse("Compare Two Users by using equal method with default constructor", firstUser.equals(secondUser));
	}
	
	@Test
	public void testTwoEqualUsersWithFirstValidObject() {
		User firstUser = new User();
		User secondUser = new User();
		firstUser.setName("Pao Im");
		firstUser.setEmail("paoim@yahoo.com");
		assertFalse("Compare Two Users by using equal method with First valid Object", firstUser.equals(secondUser));
	}
	
	@Test
	public void testTwoEqualUsersWithSecondValidObject() {
		User firstUser = new User();
		User secondUser = new User("Pao Im", "paoim@yahoo.com");
		assertFalse("Compare Two Users by using equal method with second valid object", firstUser.equals(secondUser));
	}
	
	@Test
	public void testTwoEqualUsersWithSuccess() {
		User firstUser = new User("Pao Im", "paoim@yahoo.com");
		User secondUser = new User("Pao Im", "paoim@yahoo.com");
		assertTrue("Compare Two Users by using equal method with success", firstUser.equals(secondUser));
	}
	
	@Test
	public void testUsersHashSetWithDuplicateItems() {
		Set<User> userSet = new HashSet<>();
		User firstUser = new User("Pao Im", "paoim@yahoo.com");
		User secondUser = new User("Pao Im", "paoim@yahoo.com");
		userSet.add(firstUser);
		userSet.add(secondUser);
		assertFalse("Compare Two Users by using total size of HashSet", userSet.size() == 2);
		assertTrue("Compare Two Users by using contains method in HashSet", userSet.contains(firstUser));
	}
	
	@Test
	public void testUsersHashSetWithDifferentItems() {
		Set<User> userSet = new HashSet<>();
		User firstUser = new User("Pao Im", "paoim@yahoo.com");
		User secondUser = new User("Soma Im", "somaim@gmail.com");
		userSet.add(firstUser);
		userSet.add(secondUser);
		assertTrue("Compare Two Users by using total size of HashSet", userSet.size() == 2);
		assertTrue("Compare Two Users by using contains method in HashSet", userSet.contains(firstUser));
	}
}
