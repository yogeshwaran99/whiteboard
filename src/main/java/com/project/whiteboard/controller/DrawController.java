package com.project.whiteboard.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.project.whiteboard.model.DrawingData;

@Controller
public class DrawController {

    @MessageMapping("/draw")
    @SendTo("/topic/draw")
    public DrawingData handleDraw(DrawingData data) {
        return data;
    }
}

