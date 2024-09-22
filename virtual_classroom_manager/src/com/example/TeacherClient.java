package virtual_classroom_manager.src.com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TeacherClient {
    public static void main(String[] args) {
        // Try to establish a connection to the server
        try (Socket socket = new Socket("localhost", 8080)) {
            
            // Input stream to read responses from the server
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Output stream to send commands to the server
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            // Input stream to read commands from the console
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

            // Send the role and ID of the teacher
            output.println("Teacher 1");

            String command = "start server";

            while (!command.equalsIgnoreCase("exit")) {
                // Prompt the user for a command
                System.out.print("Enter command (add_classroom / list_classroom / remove_class / add_student / list_students / add_assignment / list_submitted_assignment / list_assignments / exit): ");
                command = console.readLine();  // Read command from the console
                output.println(command);  // Send the command to the server

                // Read the server's response
                String response = input.readLine();
                System.out.println("Server: " + response);
            }
        } catch (IOException e) {
            // Handle IOException that may occur if the server is down
            System.err.println("Error: Unable to connect to the server. Please check if the server is running.");
            e.printStackTrace();
        }
    }
}

