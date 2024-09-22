package virtual_classroom_manager.src.com.example;
import java.io.PrintWriter;

public interface StudentInterface {
    void submitAssignment(String assignmentId, String classroomName, PrintWriter output);
    void listClassrooms(PrintWriter output);
    void listAssignments(String classroomName, PrintWriter output);
}
