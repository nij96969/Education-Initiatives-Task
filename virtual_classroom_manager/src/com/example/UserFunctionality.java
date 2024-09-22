package virtual_classroom_manager.src.com.example;

import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

public class UserFunctionality {

    public static void handleCommand(String userType, String userIdString, String command, PrintWriter output) {
        String[] inputCommand = command.split(" ");
        String commandName = inputCommand[0];

        if (!Utility.isValidCommand(commandName, inputCommand.length, output)) {
            return;
        }

        switch (commandName) {

            case "add_classroom": //Minimum inputCommand Size :: 2
                addClassroom(userType, userIdString, inputCommand, output);
                break;

            case "list_classroom": //Minimum inputCommand Size :: 1
                listClassroom(userType, userIdString, output);
                break;

            case "remove_class": //Minimum inputCommand Size :: 2
                removeClassroom(userType, inputCommand, output);
                break;

            case "add_student": //Minimum inputCommand Size :: 3
                addStudent(userType, inputCommand, output);
                break;

            case "add_assignment": //Minimum inputCommand Size :: 3
                addAssignment(userType, inputCommand, output);
                break;

            case "submit_assignment": //Minimum inputCommand Size :: 3
                submitAssignment(userType, userIdString, inputCommand, output);
                break;

            case "list_submitted_assignment": //Minimum inputCommand Size :: 3
                submittedAssignments(userType, userIdString, inputCommand, output);
                break;

            case "list_students": //Minimum inputCommand Size ::  2
                listStudents(userType, userIdString, inputCommand, output);
                break;

            case "list_assignments": //Minimum inputCommand Size :: 2
                listAssignments(userType, inputCommand, output);
                break;

            default:
                output.println("Invalid command: " + inputCommand[0]);
                break;
        }
    }

    private static void addClassroom(String userType, String userIdString, String[] inputCommand, PrintWriter output) {
        if (!Utility.isTeacher(userType)) {
            output.println("Only teachers can create classrooms.");
            return;
        }

        String classroomName = inputCommand[1];
        if (!Utility.doesClassroomExist(classroomName)) {
            VirtualClassRoomManager.classrooms.put(classroomName, new Classroom(classroomName, userIdString));
            output.println("Classroom " + classroomName + " has been created.");
        } else {
            output.println("Classroom " + classroomName + " already exists.");
        }
    }

    private static void listClassroom(String userType, String userIdString, PrintWriter output) {
        if (VirtualClassRoomManager.classrooms.isEmpty()) {
            output.println("No classrooms available.");
            return;
        }

        StringBuilder buildOutput = new StringBuilder();
        if (Utility.isStudent(userType)) {
            for (String className : VirtualClassRoomManager.classrooms.keySet()) {
                if (Utility.doesStudentExistInClassroom(userIdString, className)) {
                    buildOutput.append(className).append(", ");
                }
            }
            output.println(buildOutput.toString());
            return;
        }

        if (!Utility.isTeacher(userType)) {
            output.println("Invalid user role.");
            return;
        }

        for (String className : VirtualClassRoomManager.classrooms.keySet()) {
            if (Utility.isClassroomUnderSameTeacher(userIdString, className)) {
                buildOutput.append(className).append(", ");
            }
        }

        output.println(buildOutput.toString());
    }

    private static void removeClassroom(String userType, String[] inputCommand, PrintWriter output) {
        if (!Utility.isTeacher(userType)) {
            output.println("Only teachers can remove classrooms.");
            return;
        }

        String classroomName = inputCommand[1];
        if (!Utility.doesClassroomExist(classroomName)) {
            output.println("Classroom " + classroomName + " does not exist.");
            return;
        }

        VirtualClassRoomManager.classrooms.remove(classroomName);
        output.println("Classroom " + classroomName + " has been deleted.");
    }

    private static void addStudent(String userType, String[] inputCommand, PrintWriter output) {
        if (!Utility.isTeacher(userType)) {
            output.println("Only teachers can add students to classrooms.");
            return;
        }

        String studentId = inputCommand[1];
        String classroomName = inputCommand[2];
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

    private static void listStudents(String userType, String userIdString, String[] inputCommand, PrintWriter output) {
        if (!Utility.isTeacher(userType)) {
            output.println("Only teachers can list students.");
            return;
        }

        String classroomName = inputCommand[1];
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

    private static void addAssignment(String userType, String[] inputCommand, PrintWriter output) {
        if (!Utility.isTeacher(userType)) {
            output.println("Only teachers can add assignments.");
            return;
        }

        String assignmentId = inputCommand[1];
        String classroomName = inputCommand[2];
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

    private static void submitAssignment(String userType, String userIdString, String[] inputCommand, PrintWriter output) {
        if (Utility.isTeacher(userType)) {
            output.println("Only students can submit assignments.");
            return;
        }

        String assignmentName = inputCommand[1];
        String classroomName = inputCommand[2];
        if (!Utility.doesClassroomExist(classroomName)) {
            output.println("Classroom " + classroomName + " does not exist.");
            return;
        }

        if (!Utility.doesAssignmentExistInClassroom(assignmentName, classroomName)) {
            output.println("Assignment " + assignmentName + " does not exist in the classroom.");
            return;
        }

        VirtualClassRoomManager.classrooms.get(classroomName).submitAssignment(assignmentName, userIdString);
        output.println("Assignment " + assignmentName + " submitted to " + classroomName);
    }

    private static void listAssignments(String userType, String[] inputCommand, PrintWriter output) {
        String classroomName = inputCommand[1];

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

    private static void submittedAssignments(String userType, String userIdString, String[] inputCommand, PrintWriter output) {
        String assignmentName = inputCommand[1];
        String classroomName = inputCommand[2];

        if (!Utility.isClassroomUnderSameTeacher(userIdString, classroomName)) {
            output.println("Classroom " + classroomName + " does not belong to you.");
            return;
        }

        if (!Utility.doesClassroomExist(classroomName)) {
            output.println("Classroom " + classroomName + " does not exist.");
            return;
        }

        if (!Utility.doesAssignmentExistInClassroom(assignmentName, classroomName)) {
            output.println("Assignment " + assignmentName + " does not exist in the classroom.");
            return;
        }

        // Retrieve the list of students who submitted the assignment
        Set<String> submittedStudents = VirtualClassRoomManager.classrooms.get(classroomName).submittedAssignmentList(assignmentName);

        // Check if the list is empty
        if (submittedStudents == null || submittedStudents.isEmpty()) {
            output.println("No students have submitted the assignment " + assignmentName + " in classroom " + classroomName + ".");
        } else {
            // Output the list of students
            String studentList = String.join(", ", submittedStudents);
            output.println("Students who submitted the assignment '" + assignmentName + "': " + studentList);
        }
    }
}
