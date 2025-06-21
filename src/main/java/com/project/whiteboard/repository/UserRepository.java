package com.project.whiteboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.whiteboard.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);
}