package com.project.whiteboard.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.project.whiteboard.model.Room;
import com.project.whiteboard.repository.Roomrepo;

@Service
public class Roomservice {

	private final Roomrepo roomRepository;

	public Roomservice(Roomrepo roomRepository) {
		this.roomRepository = roomRepository;
	}

	public Room createRoom(String name) {
		Room room = Room.create(name);
		return roomRepository.save(room);
	}

	public Optional<Room> getRoomById(UUID id) {
		return roomRepository.findById(id);
	}
}