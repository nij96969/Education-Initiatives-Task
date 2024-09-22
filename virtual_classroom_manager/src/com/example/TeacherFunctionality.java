package virtual_classroom_manager.src.com.example;

import java.io.PrintWriter;

public class TeacherFunctionality {

    private TeacherImpl teacher;

    public TeacherFunctionality(String teacherId) {
        this.teacher = new TeacherImpl(teacherId);
    }

    public void handleCommand(String command, PrintWriter output) {
        String[] inputCommand = command.split(" ");
        String commandName = inputCommand[0];

        if (!Utility.isValidCommand(commandName, inputCommand.length, output)) {
            return;
        }

        switch (commandName.trim()) {
            case "add_classroom": // Minimum inputCommand Size :: 2
                teacher.addClassroom(inputCommand[1], output);
                break;

            case "list_classroom": // Minimum inputCommand Size :: 1
                teacher.listClassrooms(output);
                break;

            case "remove_class": // Minimum inputCommand Size :: 2
                teacher.removeClassroom(inputCommand[1], output);
                break;

            case "add_student": // Minimum inputCommand Size :: 3
                teacher.addStudent(inputCommand[1], inputCommand[2], output);
                break;

            case "add_assignment": // Minimum inputCommand Size :: 3
                teacher.addAssignment(inputCommand[1], inputCommand[2], output);
                break;

            case "list_submitted_assignment": // Minimum inputCommand Size :: 3
                teacher.listSubmittedAssignments(inputCommand[1], inputCommand[2], output);
                break;

            case "list_students": // Minimum inputCommand Size :: 1
                teacher.listStudents(inputCommand[1] , output);
                break;

            case "list_assignments": // Minimum inputCommand Size :: 1
                teacher.listAssignments(inputCommand[1], output);
                break;

            default:
                output.println("Invalid command: " + commandName);
                break;
        }
    }
}
