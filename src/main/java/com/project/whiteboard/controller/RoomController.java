package com.project.whiteboard.controller;

import java.security.Principal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import com.project.whiteboard.model.Room;
import com.project.whiteboard.model.User;
import com.project.whiteboard.repository.Roomrepo;
import com.project.whiteboard.repository.UserRepository;
import com.project.whiteboard.service.Roomservice;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin(origins = "*")
public class RoomController {

	private final Roomrepo roomRepository;
	private final Roomservice roomService;
	private final UserRepository userrepo;

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	public RoomController(Roomservice roomService, Roomrepo roomRepository, UserRepository userrepo) {
		this.roomService = roomService;
		this.roomRepository = roomRepository;
		this.userrepo = userrepo;
	}

	@PostMapping
	public ResponseEntity<Room> createRoom(@RequestBody Room requestRoom, Principal principal) {
		User creator = userrepo.findByUsername(principal.getName());
		if (creator == null) {
			return ResponseEntity.status(401).build();
		}
		Room room = roomService.createRoom(requestRoom.getName(), creator);
		return ResponseEntity.ok(room);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Room> getRoom(@PathVariable UUID id) {
		return roomService.getRoomById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteRoom(@PathVariable UUID id, Principal principal) {
		Room room = roomRepository.findById(id).orElse(null);
		if (room == null)
			return ResponseEntity.notFound().build();

		if (!room.getCreator().getUsername().equals(principal.getName())) {
			return ResponseEntity.status(403).body("Only the creator can delete this room.");
		}

		messagingTemplate.convertAndSend("/topic/room/" + id + "/status", "ROOM_CLOSED");

		roomRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
