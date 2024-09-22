package virtual_classroom_manager.src.com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
public class StudentClient {
    public static void main(String[] args) {
        // Try to establish a connection to the server
        try (Socket socket = new Socket("localhost", 8080)) {
            
            // Input stream to read responses from the server
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Output stream to send commands to the server
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            // Input stream to read commands from the console
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

            Scanner scan = new Scanner(System.in);

            // Prompt the user for their Student ID
            System.out.print("Enter your Student ID: ");
            String studentId = scan.nextLine();  // Read the student ID from the console

            // Send the role and ID of the student
            output.println("Student " + studentId);

            String command = "start server";
            try {
                while (!command.equalsIgnoreCase("exit")) {
                    // Prompt the user for a command
                    System.out.print("Enter command (list_assignments / submit_assignment / list_classroom / exit): ");
                    command = console.readLine();  // Read command from the console
                    output.println(command);  // Send the command to the server
    
                    // Read the server's response
                    String response = input.readLine();
                    System.out.println("Server: " + response);
                }
            } catch (Exception e) {
                System.err.println("Server not responding or got disconnected");
            }
            scan.close();
            
        } catch (IOException e) {
            // Handle IOException that may occur if the server is down
            System.err.println("Error: Unable to connect to the server. Please check if the server is running.");
            e.printStackTrace();
        }
    }
}
