package io.openliberty.sample.jakarta.websocket;

import java.io.IOException;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/movies/{movie}")
public class MoviesServerEndpoint {
	@OnOpen
	public void onConnect(Session session) {
		System.out.printf("Websocket opened: %s\n", session.getId().toString());
	}

	@OnMessage
	public void handleTextMessage(Session session, @PathParam("movie") String movieId, String message) throws IOException {
		session.getBasicRemote().sendText(String.format("Searching info for movie %s", movieId));
		// process movie information
	}

	@OnClose
	public void onDisconnect(Session session) {
		System.out.printf("Websocket closed for %s\n", session.getId().toString());
	}

	@OnError
	public void errorHandler(Throwable throwable) {}
}
