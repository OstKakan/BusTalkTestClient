import javax.websocket.*;
import java.net.URI;

@ClientEndpoint(encoders = JsonEncoder.class, decoders = JsonDecoder.class)
public class Client {
    Session session = null;

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
        this.session.addMessageHandler(new BusTalkMessageHandler());
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        System.out.println("Session closed. :(");
        this.session = null;
    }

    public void sendMessage(UserMessage message) {
        this.session.getAsyncRemote().sendObject(message);
    }
}
