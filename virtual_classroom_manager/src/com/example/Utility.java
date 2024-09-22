package virtual_classroom_manager.src.com.example;

import java.io.PrintWriter;

public class Utility {

    // Check if the user type is Teacher
    public static boolean isTeacher(String userType) {
        return userType.equals("Teacher");
    }

    // Check if the user type is Student
    public static boolean isStudent(String userType) {
        return userType.equals("Student");
    }

    // Check if a classroom exists in the system
    public static boolean doesClassroomExist(String className) {
        return VirtualClassRoomManager.classrooms.containsKey(className);
    }

    // Check if a student is enrolled in a specific classroom
    public static boolean doesStudentExistInClassroom(String studentId, String classroomName) {
        Classroom classroom = VirtualClassRoomManager.classrooms.get(classroomName);
        // Ensure the classroom is not null before checking enrollment
        return classroom != null && classroom.isStudentEnrolled(studentId);
    }

    // Check if an assignment exists within a specific classroom
    public static boolean doesAssignmentExistInClassroom(String assignmentName, String classroomName) {
        Classroom classroom = VirtualClassRoomManager.classrooms.get(classroomName);
        // Ensure the classroom is not null before checking assignment existence
        return classroom != null && classroom.doesAssignmentExist(assignmentName);
    }

    // Verify if the classroom belongs to the specified teacher
    public static boolean isClassroomUnderSameTeacher(String teacherId, String classroomName) {
        Classroom classroom = VirtualClassRoomManager.classrooms.get(classroomName);
        // Check if the classroom is not null before verifying the teacher
        return classroom != null && classroom.isSameTeacher(teacherId);
    }
    
    public static boolean isValidCommand(String commandName, int length, PrintWriter output) {
        switch (commandName.trim()) {
            case "add_classroom":
                if (length < 2) return printInvalidArguments(output, "add_classroom requires at least 2 arguments.");
                return true;
            case "list_classroom":
                if (length < 1) return printInvalidArguments(output, "list_classroom requires at least 1 argument.");
                return true;
            case "remove_class":
                if (length < 2) return printInvalidArguments(output, "remove_class requires at least 2 arguments.");
                return true;
            case "add_student":
                if (length < 3) return printInvalidArguments(output, "add_student requires at least 3 arguments.");
                return true;
            case "add_assignment":
                if (length < 3) return printInvalidArguments(output, "add_assignment requires at least 3 arguments.");
                return true;
            case "submit_assignment":
                if (length < 3) return printInvalidArguments(output, "submit_assignment requires at least 3 arguments.");
                return true;
            case "list_submitted_assignment":
                if (length < 3) return printInvalidArguments(output, "list_submitted_assignment requires at least 3 arguments.");
                return true;
            case "list_students":
                if (length < 2) return printInvalidArguments(output, "list_students requires at least 2 arguments.");
                return true;
            case "list_assignments":
                if (length < 2) return printInvalidArguments(output, "list_assignments requires at least 2 arguments.");
                return true;
            default:
                // Handle unknown commands
                output.println("Invalid command: " + commandName);
                return false;
        }
    }

    public static boolean printInvalidArguments(PrintWriter output, String message) {
        output.println("Invalid command: " + message);
        return false;
    }
}

