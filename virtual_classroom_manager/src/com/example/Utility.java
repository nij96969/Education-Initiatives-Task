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
        switch (commandName) {
            case "add_classroom":
                return length >= 2 || printInvalidArguments(output, "add_classroom requires at least 2 arguments.");
            case "list_classroom":
                return length >= 1 || printInvalidArguments(output, "list_classroom requires at least 1 argument.");
            case "remove_class":
                return length >= 2 || printInvalidArguments(output, "remove_class requires at least 2 arguments.");
            case "add_student":
                return length >= 3 || printInvalidArguments(output, "add_student requires at least 3 arguments.");
            case "add_assignment":
                return length >= 3 || printInvalidArguments(output, "add_assignment requires at least 3 arguments.");
            case "submit_assignment":
                return length >= 3 || printInvalidArguments(output, "submit_assignment requires at least 3 arguments.");
            case "list_submitted_assignment":
                return length >= 3 || printInvalidArguments(output, "list_submitted_assignment requires at least 3 arguments.");
            case "list_students":
                return length >= 2 || printInvalidArguments(output, "list_students requires at least 2 arguments.");
            case "list_assignments":
                return length >= 2 || printInvalidArguments(output, "list_assignments requires at least 2 arguments.");
            default:
                return false;
        }
    }

    public static boolean printInvalidArguments(PrintWriter output, String message) {
        output.println("Invalid command: " + message);
        return false;
    }
}
