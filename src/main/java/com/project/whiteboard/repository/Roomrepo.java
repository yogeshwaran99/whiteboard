package com.project.whiteboard.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.whiteboard.model.Room;

public interface Roomrepo extends JpaRepository<Room, UUID> {

}
