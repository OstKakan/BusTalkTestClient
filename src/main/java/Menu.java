import org.json.JSONObject;

import java.util.Scanner;

/**
 * Created by danie on 2015-10-03.
 */
public class Menu {
    private static Scanner scanner = new Scanner(System.in);

    public static void printMenu() {
        System.out.println();
        System.out.println("[1] Send message");
        System.out.println("[2] Get list of rooms");
        System.out.println("[3] Get list of users in room");
        System.out.println("[4] Join room");
        System.out.println("[5] Leave room");
        System.out.println("[6] Create room");
        System.out.println("[7] Set name/interests");
        System.out.println("[8] Change group");
        System.out.println();
    }

    public static UserMessage selectOption(char c) throws IllegalArgumentException {
        JSONObject json = new JSONObject();
        String chatId;
        String message;
        String name;
        String interests;
        UserMessage userMessage = new UserMessage(json);

        switch (Character.toLowerCase(c)) {
            case '1':
                System.out.println("Enter chatId:");
                chatId = scanner.nextLine();
                System.out.println("Enter message:");
                message = scanner.nextLine();
                json.put("type", Type.CHAT_MESSAGE);
                json.put("chatId", chatId);
                json.put("message", message);
                break;

            case '2':
                json.put("type", Type.LIST_OF_ALL_CHATROOMS_REQUEST);
                break;

            case '3':
                System.out.println("Enter chatId:");
                chatId = scanner.nextLine();
                json.put("type", Type.LIST_OF_USERS_IN_ROOM_REQUEST);
                json.put("chatId", chatId);
                break;

            case '4':
                System.out.println("Enter chatId:");
                chatId = scanner.nextLine();
                json.put("type", Type.JOIN_ROOM_REQUEST);
                json.put("chatId", chatId);
                break;

            case '5':
                System.out.println("Enter chatId:");
                chatId = scanner.nextLine();
                json.put("type", Type.LEAVE_ROOM_REQUEST);
                json.put("chatId", chatId);
                break;

            case '6':
                System.out.println("Enter chat name:");
                String chatName = scanner.nextLine();
                json.put("type", Type.CREATE_ROOM_REQUEST);
                json.put("chatName", chatName);
                break;

            case '7':
                System.out.println("Enter name:");
                name = scanner.nextLine();
                System.out.println("Enter interests:");
                interests = scanner.nextLine();
                json.put("type", Type.CHOOSE_NICKNAME_REQUEST);
                json.put("name", name);
                json.put("interests", interests);
                break;

            case '8':
                System.out.println("Enter group:");
                String groupId = scanner.nextLine();
                json.put("type", Type.CHANGE_GROUP_ID);
                json.put("groupId", groupId);

            default:
                throw new IllegalArgumentException("No such option");
        }

        return userMessage;
    }
}
