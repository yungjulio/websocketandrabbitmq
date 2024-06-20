package org.chatapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import org.chatapp.domain.WebSocketChatMessage;

@Controller
public class WebSocketChatController {


    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/javainuse")
    public WebSocketChatMessage sendMessage(@Payload WebSocketChatMessage webSocketChatMessage) {
        System.out.println("CHAT");
        return webSocketChatMessage;
    }

    @MessageMapping("/chat.newUser")
    @SendTo("/topic/javainuse")
    public WebSocketChatMessage newUser(@Payload WebSocketChatMessage webSocketChatMessage,
                                        SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", webSocketChatMessage.getSender());
        return webSocketChatMessage;
    }

    @MessageMapping("/chat.whisperMessage")
    public void whisperMessage(@Payload WebSocketChatMessage webSocketChatMessage) {
        System.out.println("Whisper to " + webSocketChatMessage.getWhisperReceiver());
        messagingTemplate.convertAndSend("/topic/whisper-" + webSocketChatMessage.getWhisperReceiver(), webSocketChatMessage);
    }

}