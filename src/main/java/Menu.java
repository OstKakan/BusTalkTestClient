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
        System.out.println("[C]hange name/interests");
    }

    public static String selectOption(char c) throws IllegalArgumentException {
        String jsonMessage = "";
        JSONObject json = new JSONObject();
        String chatId;
        String message;
        String name;
        String interests;

        switch (Character.toLowerCase(c)) {
            case 's':
                System.out.println("Enter chatId:");
                chatId = scanner.nextLine();
                System.out.println("Enter message:");
                message = scanner.nextLine();
                json.put("type", "chat message");
                json.put("chatId", chatId);
                json.put("message", message);
                jsonMessage = json.toString();
                break;

            case 'g':
                System.out.println("Enter chatId:");
                chatId = scanner.nextLine();
                json.put("type", "get users in room");
                json.put("chatId", chatId);
                jsonMessage = json.toString();
                break;

            case 'j':
                System.out.println("Enter chatId");
                chatId = scanner.nextLine();
                json.put("type", "join room");
                json.put("chatId", chatId);
                jsonMessage = json.toString();
                break;

            case 'l':
                System.out.println("Enter chatId");
                chatId = scanner.nextLine();
                json.put("type", "leave room");
                json.put("chatId", chatId);
                jsonMessage = json.toString();
                break;

            case 'c':
                System.out.println("Enter name:");
                name = scanner.nextLine();
                System.out.println("Enter interests:");
                interests = scanner.nextLine();
                json.put("type", "set credentials");
                json.put("name", name);
                json.put("interests", interests);
                jsonMessage = json.toString();
                break;
            default:
                throw new IllegalArgumentException("No such option");
        }

        return jsonMessage;
    }
}
