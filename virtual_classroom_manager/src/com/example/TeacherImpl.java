package virtual_classroom_manager.src.com.example;

import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

public class TeacherImpl implements TeacherInterface {

    private String userIdString;

    public TeacherImpl(String userIdString) {
        this.userIdString = userIdString;
    }

    @Override
    public void addClassroom(String classroomName, PrintWriter output) {
        if (!Utility.doesClassroomExist(classroomName)) {
            VirtualClassRoomManager.classrooms.put(classroomName, new Classroom(classroomName, userIdString));
            output.println("Classroom " + classroomName + " has been created.");
        } else {
            output.println("Classroom " + classroomName + " already exists.");
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
            if (Utility.isClassroomUnderSameTeacher(userIdString, className)) {
                buildOutput.append(className).append(", ");
            }
        }

        output.println(buildOutput.toString());
    }

    @Override
    public void removeClassroom(String classroomName, PrintWriter output) {
        if (!Utility.doesClassroomExist(classroomName)) {
            output.println("Classroom " + classroomName + " does not exist.");
            return;
        }

        VirtualClassRoomManager.classrooms.remove(classroomName);
        output.println("Classroom " + classroomName + " has been deleted.");
    }

    @Override
    public void addStudent(String studentId, String classroomName, PrintWriter output) {
        if (!Utility.doesClassroomExist(classroomName)) {
            output.println("Classroom " + classroomName + " does not exist.");
            return;
        }

        if (!Utility.doesStudentExistInClassroom(studentId, classroomName)) {
            VirtualClassRoomManager.classrooms.get(classroomName).addStudent(studentId);
            output.println("Student " + studentId + " has been added to " + classroomName);
        } else {
            output.println("Student " + studentId + " already exists in the classroom.");
        }
    }

    @Override
    public void listStudents(String classroomName, PrintWriter output) {
        if (!Utility.doesClassroomExist(classroomName)) {
            output.println("Classroom " + classroomName + " does not exist.");
            return;
        }

        if (!Utility.isClassroomUnderSameTeacher(userIdString, classroomName)) {
            output.println("Classroom " + classroomName + " does not belong to you.");
            return;
        }

        Classroom classroom = VirtualClassRoomManager.classrooms.get(classroomName);
        StringBuilder buildOutput = new StringBuilder();
        for (String studentId : classroom.listStudents()) {
            buildOutput.append(studentId).append(", ");
        }

        output.println(buildOutput.toString());
    }

    @Override
    public void addAssignment(String assignmentId, String classroomName, PrintWriter output) {
        if (!Utility.doesClassroomExist(classroomName)) {
            output.println("Classroom " + classroomName + " does not exist.");
            return;
        }

        if (!Utility.doesAssignmentExistInClassroom(assignmentId, classroomName)) {
            VirtualClassRoomManager.classrooms.get(classroomName).addAssignment(assignmentId);
            output.println("Assignment " + assignmentId + " has been added to " + classroomName);
        } else {
            output.println("Assignment " + assignmentId + " already exists in the classroom.");
        }
    }

    @Override
    public void listAssignments(String classroomName, PrintWriter output) {
        if (!Utility.doesClassroomExist(classroomName)) {
            output.println("Classroom " + classroomName + " does not exist.");
            return;
        }

        List<String> assignmentsList = VirtualClassRoomManager.classrooms.get(classroomName).listAssignments();

        if (assignmentsList.isEmpty()) {
            output.println("No assignments available in the classroom " + classroomName);
        } else {
            String assignments = String.join(", ", assignmentsList);
            output.println("Assignments in " + classroomName + ": " + assignments);
        }
    }

    @Override
    public void listSubmittedAssignments(String assignmentName, String classroomName, PrintWriter output) {
        if (!Utility.doesClassroomExist(classroomName)) {
            output.println("Classroom " + classroomName + " does not exist.");
            return;
        }

        if (!Utility.isClassroomUnderSameTeacher(userIdString, classroomName)) {
            output.println("Classroom " + classroomName + " does not belong to you.");
            return;
        }

        if (!Utility.doesAssignmentExistInClassroom(assignmentName, classroomName)) {
            output.println("Assignment " + assignmentName + " does not exist in the classroom.");
            return;
        }

        Set<String> submittedStudents = VirtualClassRoomManager.classrooms.get(classroomName).submittedAssignmentList(assignmentName);

        if (submittedStudents == null || submittedStudents.isEmpty()) {
            output.println("No students have submitted the assignment " + assignmentName + " in classroom " + classroomName + ".");
        } else {
            String studentList = String.join(", ", submittedStudents);
            output.println("Students who submitted the assignment '" + assignmentName + "': " + studentList);
        }
    }
}
