package io.openliberty.sample.jakarta.websocket;

import java.io.IOException;
import jakarta.websocket.server.ServerEndpoint;
import jakarta.websocket.OnOpen;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.Session;

@ServerEndpoint("/./invalid")
public class DemoWebSocketServerRelative {
	@OnOpen
	public void onOpen(Session session) {
		System.out.printf("Session opened.\nSession ID: %s\n", session.getId());
		try {
			session.getBasicRemote().sendText("You are connected to /a/b.");
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
