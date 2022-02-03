package io.openliberty.websockets;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import jakarta.websocket.server.ServerEndpoint;
import jakarta.websocket.OnOpen;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnClose;
import jakarta.websocket.Session;


// Followed tutorial from https://www.baeldung.com/java-websockets
@ServerEndpoint(value = "/infos")
public class Initialization {
	private static Session session;	
	
	@OnOpen
	public void OnOpen(Session session) throws IOException {
		this.session = session;
		System.out.println("Websocket opened: " + session.getId().toString());
	}
	
	@OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Program requested " + message + " using " + session.getId());
        session.getAsyncRemote().sendText("Lucky says hi");
    }

    @OnMessage
    public void notValidMessage(String message, Session session) {
        System.out.println("Program requested " + message + " using " + session.getId());
        session.getAsyncRemote().sendText("I am never shown");
    }

    @OnError
    public void onError(Session session, Throwable throwable, String test) {
        System.out.println("There was an error WebSocket error for " + session.getId() + " " + throwable.getMessage());
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("WebSocket closed for " + session.getId());
    }
}
