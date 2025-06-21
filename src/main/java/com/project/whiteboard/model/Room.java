package com.project.whiteboard.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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

    public static Room create(String name) {
        return Room.builder()
                .id(UUID.randomUUID())
                .name(name)
                .build();
    }
}
