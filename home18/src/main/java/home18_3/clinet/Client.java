package home18_3.clinet;

import home18_3.ChatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Client
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
public class Client {
    private static final Logger LOGGER = LoggerFactory.getLogger(Client.class);
    private static final String ADDRESS = "localhost";
    private static final int PORT = 2222;
    private Socket sock;
    private BufferedReader reader;
    private PrintWriter writer;
    private boolean isConnected = false;

    public static void main(String[] args) {
        Client client = new Client();
        client.getConnection();
        client.workWithConsole();
    }

    /**
     * Method create connection with server and create IncomingReader to listen server
     */
    private void getConnection() {
        try {
            sock = new Socket(ADDRESS, PORT);
            InputStreamReader inputStreamReader = new InputStreamReader(sock.getInputStream());
            reader = new BufferedReader(inputStreamReader);
            writer = new PrintWriter(sock.getOutputStream());
            isConnected = true;
        } catch (Exception e) {
            throw new ChatException("Exception in getConnection");
        }
        ListenThread(reader);
    }

    private void ListenThread(BufferedReader reader) {
        Thread IncomingReader = new Thread(new IncomingReader(reader));
        IncomingReader.start();
    }

    /**
     * Method work with console, read commands and send it to server
     */
    private void workWithConsole() {
        if (isConnected) {
            try (InputStreamReader inputStreamReader = new InputStreamReader(System.in);
                 BufferedReader reader = new BufferedReader(inputStreamReader)) {
                String consoleInput = reader.readLine();
                while (!consoleInput.equals("QUIT")) {
                    writer.println(consoleInput);
                    writer.flush();
                    consoleInput = reader.readLine();
                }
                close();
            } catch (Exception e) {
                LOGGER.error("Exception in workWithConsole", e);
            }
        }
    }

    /**
     * Method to close connection
     */
    private void close() {
        isConnected = false;
        writer.println("CLOSE");
        writer.flush();
        writer.close();
        try {
            sock.close();
        } catch (IOException e) {
            throw new ChatException("Exception in close");
        }
    }
}
