package virtual_classroom_manager.src.com.example;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class VirtualClassRoomManager {
    public static boolean startServer = true;
    public static Map<String, Classroom> classrooms = new HashMap<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server started, waiting for users to connect.....");

            while (startServer) {
                Socket socket = serverSocket.accept();
                new ClientHandler(socket).start();  // Start a new thread for each client
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
