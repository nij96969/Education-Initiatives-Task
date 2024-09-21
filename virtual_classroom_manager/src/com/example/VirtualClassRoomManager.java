package virtual_classroom_manager.src.com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class VirtualClassRoomManager {
    public static boolean startServer = true;
    private static Map<String, Classroom> classrooms = new HashMap<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server started, waiting for users to connect.....");
            
            while (startServer) {
                Socket socket = serverSocket.accept();
                new ClientHandler(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread{
        
        private Socket socket;
        private BufferedReader input;
        private PrintWriter output;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                output = new PrintWriter(socket.getOutputStream(), true);

                String userType = input.readLine(); // Read whether Teacher or Student
                System.out.println(userType + "connected.");

                String command;
                while ((command = input.readLine()) != null) {
                    handleCommand(userType, command);
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
        
        public boolean isTeacher(String userType){
            return userType.equals("Teacher") ? true : false;
        }

        public static boolean doesClassroomExist(String className) {
            // Check if the classroom exists in the map
            return classrooms.containsKey(className);
        }

        public boolean doesStudentExistInClassroom(String studentId , String classroomString){
            Classroom classroom = classrooms.get(classroomString);
            
            //Check if Student Exist in Classroom or not
            return classroom.isStudentEnrolled(studentId);
        }

        public boolean doesAssignmentExistInClassroom(String assignmentName , String classroomString){
            Classroom classroom = classrooms.get(classroomString);
            
            //Check if Assignment Exist in Classroom or not
            return classroom.doesAssignmentExist(assignmentName);
        }

        private void add_classroom(String userType , String[] inputCommand ){
            
            if(!isTeacher(userType)){
                output.println("Only teachers can create classrooms.");
                return;
            }
        
            if (!doesClassroomExist(inputCommand[1])) {
                classrooms.put(inputCommand[1], new Classroom(inputCommand[1]));
                output.println("Classroom " + inputCommand[1] + " has been created.");
            }
            else{
                output.println("Classroom " + inputCommand[1] + " already exits.");
            }
        }

        private void add_student( String userType , String[] inputCommand ){
            if(!isTeacher(userType)){
                output.println("Only teachers can create classrooms.");
                return;
            }

            if(!doesClassroomExist(inputCommand[2])){
                output.println("Classroom " + inputCommand[2] + " donot exists");
            }

            if(!doesStudentExistInClassroom(inputCommand[1] , inputCommand[2])){
                
                classrooms.get(inputCommand[2]).addStudent(inputCommand[1]);
                output.println("Student " + inputCommand[1] + " has been added to " + inputCommand[2]);
            }
            
            else{
                output.println("Student " + inputCommand[1] + " already exists");
            }
        }
        
        private void add_assignment( String userType , String[] inputCommand ){
            if(!isTeacher(userType)){
                output.println("Only teachers can create classrooms.");
                return;
            }

            if(!doesClassroomExist(inputCommand[2])){
                output.println("Classroom " + inputCommand[2] + " donot exists");
            }
            
            if(!doesAssignmentExistInClassroom(inputCommand[1] , inputCommand[2])){
                classrooms.get(inputCommand[2]).addAssignment(inputCommand[1]);
                output.println("Assignment " + inputCommand[1] + " has been added to class " + inputCommand[2]);
            }
            else{
                output.println("Assignment " + inputCommand[1] + " already exists in class" + inputCommand[2]);
            }
        }

        private void handleCommand(String userType , String command){
            String[] inputCommand = command.split(" ");
            
            switch(inputCommand[0]){
                case "add_classroom":
                    add_classroom(userType , inputCommand);
                    break;

                case "add_student":
                    add_student(userType , inputCommand);
                    break;

                case "add_assignment":
                    add_assignment(userType , inputCommand);
                    break;

                default:
                    break;
            }
        }
    }
}
