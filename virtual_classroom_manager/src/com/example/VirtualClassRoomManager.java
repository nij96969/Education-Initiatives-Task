package virtual_classroom_manager.src.com.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VirtualClassRoomManager {
    private static final Logger logger = Logger.getLogger(VirtualClassRoomManager.class.getName());
    public static boolean startServer = true;
    public static Map<String, Classroom> classrooms = new HashMap<>();
    private static Integer port = 8080;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("Server started, waiting for users to connect.....");

            while (startServer) {
                try {
                    Socket socket = serverSocket.accept();
                    new ClientHandler(socket).start();  // Start a new thread for each client
                } catch (IOException e) {
                    logger.log(Level.SEVERE, "Error accepting connection", e);
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Could not start server on port 8080", e);
        }
    }
}
