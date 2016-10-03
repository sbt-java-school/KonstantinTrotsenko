package home18_1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Class FortuneTellerClient, connect wit FortuneTellerServer
 *
 * @vesion 1.0
 * @autor Trotsenko Konstantin
 */
public class FortuneTellerClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(FortuneTellerClient.class);
    private static final int DEFAULT_PORT = 55555;
    private static InetAddress HOST;

    /**
     * Request response work
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        HOST = InetAddress.getLocalHost();
        LOGGER.info("Client Address : " + HOST);
        LOGGER.info("Enter quiz number ( Enter QUIT to end):");
        try (Socket socket = new Socket(HOST, DEFAULT_PORT);
             InputStream in = socket.getInputStream();
             OutputStream out = socket.getOutputStream();
             ObjectInputStream ois = new ObjectInputStream(in);
             ObjectOutputStream oos = new ObjectOutputStream(out);
             InputStreamReader inputStreamReader = new InputStreamReader(System.in);
             BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line = reader.readLine();
            String command;
            while (!line.equals("QUIT")) {
                oos.writeUTF(line);
                oos.flush();
                command = ois.readUTF();
                if (command.equals("SUCCESS")) {
                    LOGGER.info("Get from server Success");
                    LOGGER.info("Congratulations. Game Over");
                    return;
                } else {
                    LOGGER.info("Get from server Fail");
                }
                line = reader.readLine();
            }
            oos.writeUTF("QUIT");
            oos.flush();
        }
    }
}
