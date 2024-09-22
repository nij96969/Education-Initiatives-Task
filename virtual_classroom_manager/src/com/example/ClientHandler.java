package virtual_classroom_manager.src.com.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private String userType;
    private String userIdString;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

            String[] credentials = input.readLine().split(" ");
            userType = credentials[0];  // Teacher or Student
            userIdString = credentials[1];  // User ID

            System.out.println(userType + " connected. with userid " + userIdString);

            String command;
            while ((command = input.readLine()) != null) {
                UserFunctionality.handleCommand(userType, userIdString, command, output);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
