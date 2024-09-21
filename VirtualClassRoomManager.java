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
                
                userType = credentials[0]; // Read whether Teacher or Student
                userIdString = credentials[1];
                System.out.println(userType + "connected. with userid" + userIdString);

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
        
        public boolean isStudent(String userType){
            return userType.equals("Student") ? true : false;
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

        private void addClassroom(String userType , String[] inputCommand ){
            
            if(!isTeacher(userType)){
                output.println("Only teachers can create classrooms.");
                return;
            }
        
            if (!doesClassroomExist(inputCommand[1])) {
                classrooms.put(inputCommand[1], new Classroom(inputCommand[1] , userIdString));
                output.println("Classroom " + inputCommand[1] + " has been created.");
            }
            else{
                output.println("Classroom " + inputCommand[1] + " already exits.");
            }
        }

        private void listClassroom(String userType , String[] inputCommand) {
            if (classrooms.isEmpty()) {
                // No classrooms to list
                output.println("No classrooms available.");
            } 
            if(isStudent(userType)){
                //For Student => class list
                System.out.println("Inside list class for students");

                StringBuilder buildOutput = new StringBuilder();
                for (String className : classrooms.keySet()){
                    if(doesStudentExistInClassroom(userIdString , className)){
                        buildOutput.append(className).append(" , "); // Append key
                    }
                }
                output.println(buildOutput.toString());
            }
            else {
                //For Teacher => class list
                StringBuilder buildOutput = new StringBuilder();
                for (String key : classrooms.keySet()) {
                  
                    buildOutput.append(key).append(","); // Append key 
                }
                
                output.println(buildOutput.toString()); // Send the complete output                
            }
        }

        private void removeClassroom(String userType , String[] inputCommand){

            String classroomName = inputCommand[1];

            if(!isTeacher(userType)){
                output.println("Only teachers can remove classrooms.");
                return;
            }

            if (!doesClassroomExist(classroomName)) {
                output.println("Classroom " + classroomName + " donot exists");
                return;
            }

            classrooms.remove(classroomName);
            output.println("Classroom " + classroomName + " is deleted");

        }
        
        private void addStudent( String userType , String[] inputCommand ){

            String classroomName = inputCommand[2];
            String studentIdString = inputCommand[1];

            if(!isTeacher(userType)){
                output.println("Only teachers can add students to classrooms.");
                return;
            }

            if(!doesClassroomExist(classroomName)){
                output.println("Classroom " + classroomName + " donot exists");
            }

            if(!doesStudentExistInClassroom(studentIdString , classroomName)){
                
                classrooms.get(classroomName).addStudent(studentIdString);
                output.println("Student " + studentIdString + " has been added to " + classroomName);
            }
            
            else{
                output.println("Student " + studentIdString + " already exists");
            }
        }

        private void listStudents(String userType , String[] inputCommand){
            
            String classroomName = inputCommand[1];
            
            if(!isTeacher(userType)){
                output.println("Only teachers can list classrooms students.");
                return;
            }

            if(!doesClassroomExist(classroomName)){
                output.println("Classroom " + classroomName + " donot exists");
            }

            Classroom classroom = classrooms.get(classroomName);
            if (classroom == null) {
                output.println("Classroom not found.");
                return;
            }

            StringBuilder buildOutput = new StringBuilder();
            for(String studentName : classrooms.get(classroomName).listStudents()){
                
                buildOutput.append(studentName).append(" , "); // Append key
            }

            output.println((buildOutput.toString()));
        }

        private void addAssignment( String userType , String[] inputCommand ){

            String classroomName = inputCommand[2];
            String assignmentId = inputCommand[1];

            if(!isTeacher(userType)){
                output.println("Only teachers can add classrooms assignments.");
                return;
            }

            if(!doesClassroomExist(classroomName)){
                output.println("Classroom " + classroomName + " donot exists");
            }

            if(!doesAssignmentExistInClassroom(assignmentId , classroomName)){
                classrooms.get(classroomName).addAssignment(assignmentId);
                output.println("Assignment " + assignmentId + " has been added to class " + classroomName);
            }

            else{
                output.println("Assignment " + assignmentId + " already exists in class " +  classroomName);
            }

        }

        private void submitAssignment(String userType , String[] inputCommand){

            String assignmentName = inputCommand[1];
            String studentId = inputCommand[2];
            String className = inputCommand[3];

            if (isTeacher(userType)) {
                output.println("Only Students can submit assignments.");
                return;
            }

            if (!doesClassroomExist(className)) {
                output.println("Classroom " + className + " does not exist.");
                return;
            }

            if (!doesAssignmentExistInClassroom(assignmentName, className)) {
                output.println("Assignment " + assignmentName + " does not exist in class " + className);
                return;
            }

            classrooms.get(className).submitAssignment(assignmentName, studentId);

            output.println("Assignment " + assignmentName + " submitted by student " + studentId);

        }

        private void handleCommand(String userType , String command){
            String[] inputCommand = command.split(" ");
            
            switch(inputCommand[0].trim()){
                case "add_classroom":
                    addClassroom(userType , inputCommand);
                    break;

                case "list_classroom":
                    listClassroom(userType , inputCommand);
                    break;

                case "remove_class":
                    removeClassroom(userType, inputCommand);
                    break;

                case "add_student":
                    addStudent(userType , inputCommand);
                    break;

                case "add_assignment":
                    addAssignment(userType , inputCommand);
                    break;

                case "submit_assignment":
                    submitAssignment(userType , inputCommand);
                    break;

                case  "list_students":
                    listStudents(userType , inputCommand);
                    break;
                
                default:
                    output.println("Invalid command found");
                    break;
            }
        }
    }
}
