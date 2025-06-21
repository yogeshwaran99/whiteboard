package com.project.whiteboard.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.project.whiteboard.model.DrawingData;

@Controller
public class DrawController {

	@MessageMapping("/room/{roomId}/draw")
	@SendTo("/topic/room/{roomId}/draw")
	public DrawingData handleDraw(DrawingData data) {
		return data;
	}
}
