package com.project.whiteboard.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {
	@Id
	private UUID id;

	private String name;

	@ManyToOne
	private User creator;

	public static Room create(String name, User creator) {
		return Room.builder().id(UUID.randomUUID()).name(name).creator(creator).build();
	}

}
