package home18_3.clinet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * IncomingReader, handle messages from server
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
public class IncomingReader implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(IncomingReader.class);
    private BufferedReader reader;

    public IncomingReader(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public void run() {
        String[] data;
        String stream;
        LOGGER.info("Client become listen server");
        try {
            while ((stream = reader.readLine()) != null) {
                data = stream.split(">>");
                switch (data[0]) {
                    case "SIGNIN_OK":
                        System.out.println("Sign In successful, welcome!");
                        break;
                    case "SIGNUP_OK":
                        System.out.println("Sign Up successful, welcome!");
                        break;
                    case "SIGNIN_FAIL":
                        System.out.println("Sign In is fail, try again!");
                        break;
                    case "MESSAGE":
                        System.out.println(data[1] + ": " + data[2]);
                        break;
                    case "INCORRECT":
                        System.out.println("Incorrect input, try again!");
                        break;
                    case "USER_FAIL":
                        System.out.println("User #" + data[1] + "# not found!");
                        break;
                    case "INVALID_USER_NAME":
                        System.out.println("User with the same name already exists!");
                        break;
                    default:
                        System.out.println(stream);
                        break;
                }
            }
        } catch (Exception e) {
            LOGGER.info("Exception in IncomingReader");
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                LOGGER.info("Exception in IncomingReader close", e);
            }
        }
    }
}