package io.openliberty.websockets;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import jakarta.websocket.server.ServerEndpoint;
import jakarta.websocket.OnOpen;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.EncodeException;
import jakarta.websocket.OnClose;
import jakarta.websocket.Session;


// Followed tutorial from https://www.baeldung.com/java-websockets
@ServerEndpoint(value = "/stocks")
public class Initialization {
	private static Set<Session> sessions = new HashSet<>();	
	
    // send message to all of our suscribers
    public static void broadcastMessage(String message) {
        for (Session session : sessions) {
            try {
                session.getBasicRemote().sendObject(session);
            } catch (IOException | EncodeException e) {
                e.printStackTrace();
            }
        }
    }

	@OnOpen
	public void OnOpen(Session session) throws IOException {
        sessions.add(session);
        System.out.println("Websocket opened: " + session.getId().toString());
	}
	
	@OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Program requested " + message + " using " + session.getId());
        try {
            session.getBasicRemote().sendObject(session);
        } catch (IOException | EncodeException e) {
            e.printStackTrace();
        }
    }
 
    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("There was an error WebSocket error for " + session.getId() + " " + throwable.getMessage());
    }
 
    @OnClose
    public void onClose(Session session) {
        System.out.println("WebSocket closed for " + session.getId());
        sessions.remove(session);
    }
}
