package home18_3.server;

import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Simple imitation data base
 *
 * @author Trotsenko Konstantin
 * @version 1.0
 */
public class DataBase {
    static ConcurrentHashMap<String, String> loginPassword = new ConcurrentHashMap<>();
    static ConcurrentHashMap<String, PrintWriter> loginClient = new ConcurrentHashMap<>();
}
