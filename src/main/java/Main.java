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

        String option;
        UserMessage userMessage;
        while (true) {
            Menu.printMenu();
            option = scanner.nextLine();
            userMessage = Menu.selectOption(option.charAt(0));
            client.sendMessage(userMessage);
        }
    }

    private static void initConnection() throws URISyntaxException {
        System.out.println("Connecting...");
        client = new Client(new URI("ws://localhost:8080/chat"));
    }
}
