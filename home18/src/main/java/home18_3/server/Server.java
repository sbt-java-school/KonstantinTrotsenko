package home18_3.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
public class Server {
    private final Logger LOGGER = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) {
        Server server = new Server();
        server.startServer();
    }

    /**
     * Create connection and create thread for each client
     */
    private void startServer() {
        DataBase dataBase = new DataBase();
        LOGGER.info("Server Listening......");
        try {
            ServerSocket serverSock = new ServerSocket(2222);
            while (true) {
                Socket clientSock = serverSock.accept();
                LOGGER.info("Connection Established with " + clientSock.getRemoteSocketAddress());
                PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
                Thread listener = new Thread(new ClientHandler(clientSock, writer));
                listener.start();
            }
        } catch (Exception e) {
            LOGGER.error("Exception in startServer", e);
        }
    }
}
