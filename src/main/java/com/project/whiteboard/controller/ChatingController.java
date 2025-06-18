package com.project.whiteboard.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.project.whiteboard.model.*;


@Controller
public class ChatingController {
  @MessageMapping("/message")
  @SendTo("/topic/text")
  public Chating chating(Message message) throws Exception {
    return new Chating(HtmlUtils.htmlEscape(message.content()) );
  }

}
