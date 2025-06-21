package com.project.whiteboard.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.whiteboard.model.Room;
import com.project.whiteboard.repository.Roomrepo;
import com.project.whiteboard.service.Roomservice;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin(origins = "*")
public class RoomController {
	private Roomrepo roomRepository;
	private final Roomservice roomService;

	public RoomController(Roomservice roomService, Roomrepo roomRepository) {
		this.roomService = roomService;
		this.roomRepository = roomRepository;
	}

	@PostMapping
	public ResponseEntity<Room> createRoom(@RequestParam String name) {
		Room room = roomService.createRoom(name);
		return ResponseEntity.ok(room);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Room> getRoom(@PathVariable UUID id) {
		return roomService.getRoomById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/api/rooms/{id}")
	public ResponseEntity<?> deleteRoom(@PathVariable UUID id) {
		roomRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
