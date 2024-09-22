package virtual_classroom_manager.src.com.example;

import java.io.PrintWriter;
import java.util.List;

public class StudentImpl implements StudentInterface {
    
    private String userIdString;

    public StudentImpl(String userIdString) {
        this.userIdString = userIdString;
    }

    @Override
    public void submitAssignment(String assignmentId, String classroomName, PrintWriter output) {
        if (Utility.isTeacher(userIdString)) {
            output.println("Only students can submit assignments.");
            return;
        }

        if (!Utility.doesClassroomExist(classroomName)) {
            output.println("Classroom " + classroomName + " does not exist.");
            return;
        }

        if (!Utility.doesAssignmentExistInClassroom(assignmentId, classroomName)) {
            output.println("Assignment " + assignmentId + " does not exist in the classroom.");
            return;
        }

        VirtualClassRoomManager.classrooms.get(classroomName).submitAssignment(assignmentId, userIdString);
        output.println("Assignment " + assignmentId + " submitted to " + classroomName);
    }

    @Override
    public void listAssignments(String classroomName, PrintWriter output) {
        if (!Utility.doesClassroomExist(classroomName)) {
            output.println("Classroom " + classroomName + " does not exist.");
            return;
        }

        // Get the list of assignments from the classroom
        List<String> assignmentsList = VirtualClassRoomManager.classrooms.get(classroomName).listAssignments();

        if (assignmentsList.isEmpty()) {
            output.println("No assignments available in the classroom " + classroomName);
        } else {
            // Convert the list to a comma-separated string
            String assignments = String.join(", ", assignmentsList);
            output.println("Assignments in " + classroomName + ": " + assignments);
        }
    }

    @Override
    public void listClassrooms(PrintWriter output) {
        if (VirtualClassRoomManager.classrooms.isEmpty()) {
            output.println("No classrooms available.");
            return;
        }

        StringBuilder buildOutput = new StringBuilder();
        for (String className : VirtualClassRoomManager.classrooms.keySet()) {
            if (Utility.doesStudentExistInClassroom(userIdString, className)) {
                buildOutput.append(className).append(", ");
            }
        }
        output.println(buildOutput.toString());
    }
}
