package io.openliberty.sample.jakarta.websocket;

import java.io.IOException;
import jakarta.websocket.server.ServerEndpoint;
import jakarta.websocket.OnOpen;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.Session;

@ServerEndpoint("/{var}/b")
public class DemoWebSocketServerVarB {
	@OnOpen
	public void onOpen(Session session) {
		System.out.printf("Session opened.\nSession ID: %s\n", session.getId());
		try {
			session.getBasicRemote().sendText("You are connected to /{var}/b.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@OnClose
	public void onClose(Session session) {
		System.out.printf("Session closed.\n");
	}

	@OnMessage
	public String onMessage(String message) {
		if (message.equals("ping"))
			return "pong";
		return message;
	}
}
