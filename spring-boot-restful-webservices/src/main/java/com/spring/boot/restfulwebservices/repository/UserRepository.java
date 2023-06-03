package com.spring.boot.restfulwebservices.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.restfulwebservices.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public Optional<User>findByFirstNameAndLastNameIgnoreCase(String firstName,String lastName);
	
	public Optional<User> findByEmail(String email);
}
