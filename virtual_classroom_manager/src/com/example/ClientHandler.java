package virtual_classroom_manager.src.com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientHandler extends Thread {
    private static final Logger logger = Logger.getLogger(ClientHandler.class.getName());
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private String userType;
    private String userIdString;
    private TeacherFunctionality teacherFunctionality;
    private StudentFunctionality studentFunctionality;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

            // Read and validate user credentials
            String[] credentials = input.readLine().split(" ");
            
            if (credentials.length != 2) {
                output.println("Invalid credentials. Connection will be closed.");
                return;
            }
            
            userType = credentials[0];  // Teacher or Student
            userIdString = credentials[1];  // User ID

            logger.info(userType + " connected with user ID " + userIdString);

            // Initialize functionality based on user role
            if (Utility.isTeacher(userType)) {
                teacherFunctionality = new TeacherFunctionality(userIdString);
            } else if (Utility.isStudent(userType)) {
                // Assuming StudentImpl can be created or retrieved by userIdString
                StudentImpl student = getStudentById(userIdString);
                studentFunctionality = new StudentFunctionality(student);
            } else {
                output.println("Invalid user type. Connection will be closed.");
                return;
            }

            String command;
            while ((command = input.readLine()) != null) {
                if (command.equalsIgnoreCase("exit")) {
                    output.println("Closing connection. Goodbye!");
                    break;  // Break the loop if the user types "exit"
                }
                
                // Handle the command and catch any potential exceptions
                try {
                    if (Utility.isTeacher(userType)) {
                        teacherFunctionality.handleCommand(command, output);
                    } else {
                        studentFunctionality.handleCommand(command, output);
                    }
                } catch (Exception e) {
                    output.println("Error processing command: " + e.getMessage());
                    logger.log(Level.SEVERE, "Error processing command: " + command, e);
                }
            }
        } catch (SocketException e) {
            logger.warning("Connection is lost with user ID " + userIdString);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "I/O error occurred for user ID " + userIdString, e);
        } finally {
            closeConnection();
        }
    }

    // Method to get the student object by ID (stub implementation)
    private StudentImpl getStudentById(String userIdString) {
        // Implement logic to retrieve the StudentImpl object by userIdString
        return new StudentImpl(userIdString); // Placeholder
    }

    // Close resources and log disconnection
    private void closeConnection() {
        try {
            if (input != null) input.close();
            if (output != null) output.close();
            if (socket != null) socket.close();
            logger.info(userType + " with user ID " + userIdString + " has disconnected.");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error closing resources for user ID " + userIdString, e);
        }
    }
}
