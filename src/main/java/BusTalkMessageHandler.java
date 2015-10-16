import org.json.JSONArray;
import org.json.JSONObject;

import javax.websocket.MessageHandler;

public class BusTalkMessageHandler implements MessageHandler.Whole<UserMessage> {

    @Override
    public void onMessage(UserMessage message) {
        int type = message.getInt("type");

        switch (type) {
            case MessageType.NEW_USER_IN_CHAT_NOTIFICATION: {
                String user = message.getString("name");
                String interests = message.getString("interests");
                int chatId = message.getInt("chatId");
                System.out.println(user + " connected to chat " + chatId);
                break;
            }
            case MessageType.LIST_OF_CHATROOMS_NOTIFICATION: {
                JSONArray array = message.getJSONArray("chatrooms");

                System.out.println("--- Chat rooms ---");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    System.out.println("* " + object.getString("name") + " : " + object.getInt("chatId"));
                }
                break;
            }
            case MessageType.LIST_OF_USERS_IN_CHAT_NOTIFICATION: {
                JSONArray array = message.getJSONArray("users");

                System.out.println("--- Users in chat room ---");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    System.out.println("* " + object.getString("name") + " : " + object.getString("interests"));
                }
                break;
            }
            case MessageType.CREDENTIAL_CHANGE_NOTIFICATION: {
                break;
            }
            case MessageType.CHAT_MESSAGE_NOTIFICATION: {
                int chatId = message.getInt("chatId");
                String sender = message.getString("sender");
                String chatMessage = message.getString("message");
                String time = message.getString("time");
                System.out.println("(" + chatId + ")[" + time +"] " + sender +": " + chatMessage);
                break;
            }
            case MessageType.USER_LEFT_ROOM_NOTIFICATION: {
                String user = message.getString("name");
                int chatId = message.getInt("chatId");
                System.out.println(user + " left chat " + chatId);
                break;
            }
            case MessageType.ROOM_DELETED_NOTIFICATION: {
                int chatId = message.getInt("chatId");
                System.out.println("Chat " + chatId + " was removed.");
                break;
            }
            case MessageType.ROOM_CREATED_NOTIFICATION: {
                int chatId = message.getInt("chatId");
                String title = message.getString("title");
                System.out.println("Chat " + title + " (" + chatId + ") was created");
                break;
            }

            case MessageType.NAME_AND_INTEREST_SET: {
                boolean succeeded = message.getBoolean("succeeded");
                if (succeeded) {
                    System.out.println("Name and interests were set successfully.");
                } else {
                    System.out.println("Unable to set name and interests. Please try another name");
                }
                break;
            }
        }
    }
}
