import org.json.JSONObject;

import java.util.Scanner;

/**
 * Created by danie on 2015-10-03.
 */
public class Menu {
    private static Scanner scanner = new Scanner(System.in);

    public static void printMenu() {
        System.out.println("[S]end message");
        System.out.println("[G]et list of rooms");
        System.out.println("[J]oin room");
        System.out.println("[L]eave room");
        System.out.println("[C]reate room");
        System.out.println("S[e]t name/interests");
    }

    public static UserMessage selectOption(char c) throws IllegalArgumentException {
        JSONObject json = new JSONObject();
        String chatId;
        String message;
        String name;
        String interests;
        UserMessage userMessage = new UserMessage(json);

        switch (Character.toLowerCase(c)) {
            case 's':
                System.out.println("Enter chatId:");
                chatId = scanner.nextLine();
                System.out.println("Enter message:");
                message = scanner.nextLine();
                json.put("type", Type.CHAT_MESSAGE);
                json.put("chatId", chatId);
                json.put("message", message);
                break;

            case 'g':
                json.put("type", Type.LIST_OF_ALL_CHATROOMS_REQUEST);
                break;

            case 'j':
                System.out.println("Enter chatId");
                chatId = scanner.nextLine();
                json.put("type", Type.JOIN_ROOM_REQUEST);
                json.put("chatId", chatId);
                break;

            case 'l':
                System.out.println("Enter chatId");
                chatId = scanner.nextLine();
                json.put("type", Type.LEAVE_ROOM_REQUEST);
                json.put("chatId", chatId);
                break;

            case 'c':
                json.put("type", Type.CREATE_ROOM_REQUEST);
                break;

            case 'e':
                System.out.println("Enter name:");
                name = scanner.nextLine();
                System.out.println("Enter interests:");
                interests = scanner.nextLine();
                json.put("type", Type.CHOOSE_NICKNAME_REQUEST);
                json.put("name", name);
                json.put("interests", interests);
                break;
            default:
                throw new IllegalArgumentException("No such option");
        }

        return userMessage;
    }
}
