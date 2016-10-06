package home18_1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

/**
 * Class ChatServerExecutor thread to work with each client separately
 *
 * @version 1.0
 * @author Trotsenko Konstantin
 */
public class FortuneTellerExecutor implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(FortuneTellerExecutor.class);
    private final int quizNumber;
    private final Socket socket;
    private String line;
    private String ip;

    public FortuneTellerExecutor(Socket socket, int quizNumber) {
        this.socket = socket;
        this.quizNumber = quizNumber;
    }

    /**
     * Request response work
     */
    @Override
    public void run() {
        ip = socket.getRemoteSocketAddress().toString();
        try (InputStream in = socket.getInputStream();
             OutputStream out = socket.getOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(out);
             ObjectInputStream ois = new ObjectInputStream(in)) {
            LOGGER.info(String.valueOf(quizNumber));
            line = ois.readUTF();
            while (!line.equals("QUIT")) {
                LOGGER.info("Get from Client " + ip + "   " + line);
                if (line.equals(String.valueOf(quizNumber))) {
                    LOGGER.info("Response to Client " + ip + "   Success");
                    oos.writeUTF("SUCCESS");
                    oos.flush();
                    return;
                } else {
                    LOGGER.info("Response to Client " + ip + "   Fail");
                    oos.writeUTF("FAIL");
                    oos.flush();
                }
                line = ois.readUTF();
            }
            LOGGER.info("Client " + ip + " Closed");
        } catch (Exception e) {
            LOGGER.error("Exception in executor", e);
        } finally {
            LOGGER.info("Connection Closing..");
            try {
                socket.close();
            } catch (IOException e) {
                LOGGER.error("Socket close", e);
            }
        }
    }
}
