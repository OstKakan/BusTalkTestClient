import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

/**
 * Created by danie on 2015-09-30.
 */
public class Main {
    private static Client client;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException, URISyntaxException {
        initConnection();

        String option, message;
        while (true) {
            Menu.printMenu();
            option = scanner.nextLine();
            message = Menu.selectOption(option.charAt(0));
            client.sendMessage(message);
        }
    }

    private static void initConnection() throws URISyntaxException {
        System.out.println("Enter you nick: ");
        String nick = scanner.nextLine();
        System.out.println("Enter your interests: ");
        String interests = scanner.nextLine();

        System.out.println("Connecting...");
        client = new Client(new URI("ws://localhost:8080/BusTalkServer-1.0-SNAPSHOT/chat"));

        client.addMessageHandler(new Client.MessageHandler() {
            @Override
            public void handleMessage(String message) {
                System.out.println(message);
            }
        });

        JSONObject createUser = new JSONObject();
        createUser.put("type", "set credentials");
        createUser.put("name", nick);
        createUser.put("interests", interests);

        // Ask to connect
        client.sendMessage(createUser.toString());
    }
}
