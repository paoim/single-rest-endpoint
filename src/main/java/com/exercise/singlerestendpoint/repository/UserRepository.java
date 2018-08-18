package com.exercise.singlerestendpoint.repository;

import org.springframework.data.repository.CrudRepository;

import com.exercise.singlerestendpoint.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
