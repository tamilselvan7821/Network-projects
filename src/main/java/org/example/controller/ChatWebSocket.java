package org.example.controller;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Set;
import java.util.stream.Collectors;

@ServerEndpoint("/chat")
public class ChatWebSocket {
    private static ConcurrentHashMap<String, Session> users = new ConcurrentHashMap<>();
    private String userName;

    @OnOpen
    public void onOpen(Session session) {
        // Wait for username
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        if (userName == null) {
            // First message is username
            userName = message;
            users.put(userName, session);
            broadcastOnlineUsers();
        } else {
            // Message format: receiver:message
            String[] parts = message.split(":", 2);
            if (parts.length == 2) {
                String receiver = parts[0];
                String msg = parts[1];
                Session receiverSession = users.get(receiver);
                if (receiverSession != null) {
                    try {
                        receiverSession.getBasicRemote().sendText(userName + ": " + msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @OnClose
    public void onClose(Session session) {
        if (userName != null) {
            users.remove(userName);
            broadcastOnlineUsers();
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("Error: " + throwable.getMessage());
    }

    private void broadcastOnlineUsers() {
        Set<String> onlineUsers = users.keySet();
        String userList = "online:" + String.join(",", onlineUsers);
        for (Session s : users.values()) {
            try {
                s.getBasicRemote().sendText(userList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
