package home18_3.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class ClientHandler, handle messages from client
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
public class ClientHandler implements Runnable {
    private final Logger LOGGER = LoggerFactory.getLogger(ClientHandler.class);
    private boolean validate = false;
    private BufferedReader reader;
    private Socket sock;
    private PrintWriter client;
    private String user;
    private static ConcurrentHashMap<String, String> loginPassword = DataBase.loginPassword;
    private static ConcurrentHashMap<String, PrintWriter> loginClient = DataBase.loginClient;

    public ClientHandler(Socket clientSocket, PrintWriter writer) {
        client = writer;
        try {
            sock = clientSocket;
            InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
            reader = new BufferedReader(isReader);
        } catch (Exception e) {
            LOGGER.error("Exception in ClientHandler constructor", e);
        }
    }

    /**
     * Main method to handle messages from client
     */
    @Override
    public void run() {
        String message;
        String[] data;
        try {
            while ((message = reader.readLine()) != null) {
                data = message.split(">>");
                switch (data[0]) {
                    case "SIGNIN":
                        if (loginPassword.containsKey(data[1])) {
                            if (loginPassword.get(data[1]).equals(data[2])) {
                                loginClient.replace(data[1], loginClient.get(data[1]), client);
                                client.println("SIGNIN_OK");
                                client.flush();
                                validate = true;
                                user = data[1];
                            } else {
                                client.println("SIGNIN_FAIL");
                                client.flush();
                                break;
                            }
                        } else {
                            client.println("SIGNIN_FAIL");
                            client.flush();
                        }
                        break;
                    case "SIGNUP":
                        if (!loginPassword.containsKey(data[1])) {
                            loginPassword.put(data[1], data[2]);
                            loginClient.put(data[1], client);
                            client.println("SIGNUP_OK");
                            client.flush();
                            validate = true;
                            user = data[1];
                            tellEveryone("MESSAGE>>" + user + ">>Go into the Chat", client);
                        } else {
                            client.println("INVALID_USER_NAME");
                            client.flush();
                        }
                        break;
                    case "TELLEVN":
                        if (validate) {
                            tellEveryone("MESSAGE>>" + user + ">>" + data[1], client);
                        } else {
                            client.println("SIGNIN_FAIL");
                            client.flush();
                        }
                        break;
                    case "MESSAGE":
                        if (validate) {
                            if (loginClient.containsKey(data[1])) {
                                loginClient.get(data[1]).println("MESSAGE>>" + user + ">>" + data[2]);
                                loginClient.get(data[1]).flush();
                            } else {
                                client.println("USER_FAIL>>" + data[1]);
                                client.flush();
                            }
                        } else {
                            client.println("SIGNIN_FAIL");
                            client.flush();
                        }
                        break;
                    case "CLOSE":
                        if (validate) {
                            tellEveryone("MESSAGE>>" + user + ">>Leave the Chat", client);
                            LOGGER.info("Client " + sock.getRemoteSocketAddress() + " leave");
                            loginClient.remove(client);
                        }
                        break;
                    default:
                        client.println("INCORRECT");
                        client.flush();
                        break;
                }
            }
        } catch (Exception e) {
            LOGGER.info("Exception in ClientHandler run");
            loginClient.remove(client);
        }
    }

    /**
     *  Method to send message to all online clients
     * @param message to send
     * @param client to avoid from delivery
     */
    private void tellEveryone(String message, PrintWriter client) {
        for (Map.Entry<String, PrintWriter> entry : loginClient.entrySet()) {
            if (!entry.getValue().equals(client)) {
                try {
                    entry.getValue().println(message);
                    entry.getValue().flush();
                } catch (Exception e) {
                    LOGGER.error("Exception in tellEveryone", e);
                }
            }
        }
    }
}

