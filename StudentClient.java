package virtual_classroom_manager.src.com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class StudentClient {
    public static void main(String[] args) {
        // Define the list of commands you want to pass
        String[] commands = {
            "list_students nijanand",
            "submit_assignment 1 1 nij",
            "list_classroom 1"
        };

        try (Socket socket = new Socket("localhost", 8080)) {
            
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

            output.println("Student 1"); // Send the role

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
