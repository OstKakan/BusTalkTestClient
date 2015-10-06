import javax.websocket.*;
import java.net.URI;

@ClientEndpoint
public class Client {
    Session session = null;
    MessageHandler messageHandler;

    public Client(URI endpointURI) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            System.out.println("Connecting to " + endpointURI.toString() + "...");
            Session session = container.connectToServer(this, endpointURI);
            System.out.println("Connected! Your session id is: " + (session.toString().equals("") ? "NULL" : session.getId().toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Session opened!");
        this.session = session;
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        System.out.println("Session closed. :(");
        this.session = null;
    }

    @OnMessage
    public void onMessage(String message) {
        if (this.messageHandler != null) {
            this.messageHandler.handleMessage(message);
        }
    }

    public void addMessageHandler(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    public void sendMessage(String message) {
        this.session.getAsyncRemote().sendText(message);
    }

    public static interface MessageHandler {
        public void handleMessage(String message);
    }
}
