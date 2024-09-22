package virtual_classroom_manager.src.com.example;

import java.io.PrintWriter;

public interface TeacherInterface {
    void addClassroom(String classroomName, PrintWriter output);
    void listClassrooms(PrintWriter output);
    void removeClassroom(String classroomName, PrintWriter output);
    void addStudent(String studentId, String classroomName, PrintWriter output);
    void listStudents(String classroomName, PrintWriter output);
    void addAssignment(String assignmentId, String classroomName, PrintWriter output);
    void listAssignments(String classroomName, PrintWriter output);
    void listSubmittedAssignments(String assignmentName, String classroomName, PrintWriter output);
}
