package virtual_classroom_manager.src.com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TeacherClient {
    public static void main(String[] args) {
        // Define the list of commands you want to pass
        String[] commands = {
            "add_classroom nij",
            "add_classroom nij",
            "add_classroom nijanand",
            "add_classroom nijanand1",
            "add_classroom nijanand2",
            "add_classroom nij",
            "add_student 1 nij",
            "add_student 1 nij",
            "add_student 1 nijanand1",
            "add_student 1 nijanand2",
            "add_assignment 1 nij",
            "add_assignment 1 nij",
            "list_classroom",
            "remove_class nijanand",
            "remove_class nijanand",
            "list_students nij",
            "list_students nijanand",
            "Invalid command",
            "submit_assignment 1 1 nij",
            "list_students nij",
            "completed"
        };

        try (Socket socket = new Socket("localhost", 8080)) {
            
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

            output.println("Teacher 1"); // Send the role

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
