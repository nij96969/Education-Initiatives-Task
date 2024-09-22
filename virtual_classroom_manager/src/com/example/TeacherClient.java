package virtual_classroom_manager.src.com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TeacherClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8080)) {
            
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

            output.println("Teacher 3"); // Send the role and Id

            String command;

            while (true) {
                System.out.print("Enter command (add_classroom / add_student / schedule_assignment / list_student): ");
                command = console.readLine();
                output.println(command); // Send command to server

                String response = input.readLine();
                System.out.println("Server: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

