package virtual_classroom_manager.src.com.example;

import java.io.PrintWriter;

public class StudentFunctionality {

    private StudentImpl student;

    public StudentFunctionality(StudentImpl student) {
        this.student = student;
    }

    public void handleCommand(String command, PrintWriter output) {
        String[] inputCommand = command.split(" ");
        String commandName = inputCommand[0];

        if (!Utility.isValidCommand(commandName, inputCommand.length, output)) {
            return;
        }

        switch (commandName.trim()) {
            case "list_assignments": // Minimum inputCommand Size :: 1
                student.listAssignments(inputCommand[1] , output);
                break;

            case "submit_assignment": // Minimum inputCommand Size :: 3
                student.submitAssignment(inputCommand[1], inputCommand[2], output);
                break;

            case "list_classroom": // Minimum inputCommand Size :: 1
                student.listClassrooms(output);
                break;

            default:
                output.println("Invalid command: " + commandName);
                break;
        }
    }
}
