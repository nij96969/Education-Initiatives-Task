package virtual_classroom_manager.src.com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TeacherClient2 {
    public static void main(String[] args) {
        // Define the list of commands you want to pass
        String[] commands = {
            "add_classroom nirvanad",
            "add_student 1 nirvanad",
            "completed"
        };

        try (Socket socket = new Socket("localhost", 8080)) {
            
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

            output.println("Teacher 2"); // Send the role

            // Loop over the predefined commands
            for (int i = 0; i < commands.length; i++) {
                String command = commands[i]; // Get the current command
                
                // Send the command to the server
                output.println(command);
                
                // Get the response from the server
                String response = input.readLine();
                System.out.println("Server: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
