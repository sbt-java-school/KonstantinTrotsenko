package home18_1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * Class FortuneTellerServer to create ChatServerExecutor fo each request
 *
 * @version 1.0
 * @author Trotsenko Konstantin
 */
public class FortuneTellerServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(FortuneTellerServer.class);
    private static final int DEFAULT_PORT = 55555;
    private static int quizNumber;
    private static Random rand = new Random();
    private static Socket socket;

    public static void main(String[] args) throws IOException {
        LOGGER.info("Server Listening......");
        try (ServerSocket serverSocket = new ServerSocket(DEFAULT_PORT, 10)) {
            while (true) {
                try {
                    socket = serverSocket.accept();
                    LOGGER.info("Connection Established");
                    quizNumber = getQuizNumber();
                    Runnable fortuneExecutor = new FortuneTellerExecutor(socket, quizNumber);
                    Thread thread = new Thread(fortuneExecutor);
                    thread.start();
                } catch (Exception e) {
                    LOGGER.error("Connection Error", e);
                }
            }
        }
    }

    /**
     * Method to generate random value
     *
     * @return random value
     */
    public static int getQuizNumber() {
        return rand.nextInt(11);
    }
}
