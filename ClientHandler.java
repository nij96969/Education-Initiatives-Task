// package virtual_classroom_manager.src.com.example;

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.io.PrintWriter;
// import java.net.Socket;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// // Client handler class to handle teachers and students
// public class ClientHandler extends Thread {

//     private static Map<String, Classroom> classrooms = new HashMap<>();

//     private Socket socket;
//     private BufferedReader input;
//     private PrintWriter output;

//     public ClientHandler(Socket socket) {
//         this.socket = socket;
//     }

//     @Override
//     public void run() {
//         try {
//             input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//             output = new PrintWriter(socket.getOutputStream(), true);

//             String userType = input.readLine(); // Read whether Teacher or Student
//             System.out.println(userType + "connected.");

//             String command;
//             while ((command = input.readLine()) != null) {
//                 handleCommand(userType, command);
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         } finally {
//             try {
//                 socket.close();
//             } catch (IOException e) {
//                 e.printStackTrace();
//             }
//         }
//     }

//     private void handleCommand(String userType, String command) {
//         String[] tokens = command.split(" ");

//         switch (tokens[0]) {
//             case "add_classroom":
//                 if (userType.equals("Teacher")) {
//                     classrooms.put(tokens[1], new Classroom(tokens[1]));
//                     output.println("Classroom " + tokens[1] + " has been created.");
//                 } else {
//                     output.println("Only teachers can create classrooms.");
//                 }
//                 break;

//             case "add_student":
//                 if (userType.equals("Teacher")) {
//                     Classroom classroom = classrooms.get(tokens[1]);
//                     if (classroom != null) {
//                         classroom.addStudent(tokens[2]); // tokens[2] is student ID
//                         output.println("Student " + tokens[2] + " has been enrolled in " + tokens[1] + ".");
//                     } else {
//                         output.println("Classroom does not exist.");
//                     }
//                 } else {
//                     output.println("Only teachers can add students.");
//                 }
//                 break;

//             case "schedule_assignment":
//                 if (userType.equals("Teacher")) {
//                     Classroom classroom = classrooms.get(tokens[1]);
//                     if (classroom != null) {
//                         classroom.addAssignment(tokens[2]); // tokens[2] is assignment name
//                         output.println("Assignment for " + tokens[1] + " has been scheduled.");
//                     } else {
//                         output.println("Classroom does not exist.");
//                     }
//                 } else {
//                     output.println("Only teachers can schedule assignments.");
//                 }
//                 break;

//             case "view_assignments":
//                 if (userType.equals("Student")) {
//                     Classroom classroom = classrooms.get(tokens[1]);
//                     if (classroom != null && classroom.isStudentEnrolled(tokens[2])) {
//                         List<String> assignments = classroom.getAssignments();
//                         output.println("Assignments for " + tokens[1] + ": " + assignments);
//                     } else {
//                         output.println("You are not enrolled in this classroom or it doesn't exist.");
//                     }
//                 } else {
//                     output.println("Only students can view assignments.");
//                 }
//                 break;

//             case "submit_assignment":
//                 if (userType.equals("Student")) {
//                     Classroom classroom = classrooms.get(tokens[1]);
//                     if (classroom != null && classroom.isStudentEnrolled(tokens[2])) {
//                         classroom.submitAssignment(tokens[3], tokens[2]); // tokens[3] is assignment name, tokens[2] is student ID
//                         output.println("Assignment " + tokens[3] + " submitted by student " + tokens[2] + " in " + tokens[1]);
//                     } else {
//                         output.println("You are not enrolled in this classroom or it doesn't exist.");
//                     }
//                 } else {
//                     output.println("Only students can submit assignments.");
//                 }
//                 break;

//             default:
//                 output.println("Invalid command.");
//         }
//     }
// }
