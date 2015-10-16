import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;
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

            if (option.length() > 0) {
                try {
                    userMessage = Menu.selectOption(option);
                    client.sendMessage(userMessage);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("You must enter a number");
            }
        }
    }

    private static void initConnection() throws URISyntaxException {
        System.out.println("Connecting...");
        String address = readServerAddress();

        if(address != null) {
            client = new Client(new URI(address));
        } else {
            System.out.println("Make sure you have a config.properties file in your root folder. It should look " +
                    "something like this:");
            System.out.println("server=[address]");
            System.out.println("For example:");
            System.out.println("server=ws://localhost:8080/chat");
        }
    }

    private static String readServerAddress() {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("config.properties");

            prop.load(input);
        } catch (IOException e) {

        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return (String) prop.get("server");
    }
}
