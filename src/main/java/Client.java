import org.json.JSONObject;

import javax.websocket.*;
import java.net.URI;

@ClientEndpoint(encoders = JsonEncoder.class, decoders = JsonDecoder.class)
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
    public void onMessage(UserMessage message) {
        int type = message.getInt("type");

        switch (type) {
            case Type.NEW_USER_IN_CHAT_NOTIFICATION: {
                String user = message.getString("user");
                String interests = message.getString("interests");
                int chatId = message.getInt("chatId");
                System.out.println(user + " connected to chat " + chatId);
                break;
            }
            case Type.LIST_OF_CHATROOMS_NOTIFICATION: {
                break;
            }
            case Type.LIST_OF_USERS_IN_CHAT_NOTIFICATION: {

                break;
            }
            case Type.CREDENTIAL_CHANGE_NOTIFICATION: {
                break;
            }
            case Type.CHAT_MESSAGE_NOTIFICATION: {
                int chatId = message.getInt("chatId");
                String user = message.getString("user");
                String chatMessage = message.getString("message");
                String time = message.getString("time");
                System.out.println(String.format("({0}[{1}] {2}: {3}", new Object[] {chatId, time, user, chatMessage}));
                break;
            }
            case Type.USER_LEFT_ROOM_NOTIFICATION:
                break;
            case Type.ROOM_DELETED_NOTIFICATION:
                break;
            case Type.ROOM_CREATED_NOTIFICATION:
                break;
        }
    }

    public void addMessageHandler(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    public void sendMessage(UserMessage message) {
        this.session.getAsyncRemote().sendObject(message);
    }

    public static interface MessageHandler {
        public void handleMessage(String message);
    }
}