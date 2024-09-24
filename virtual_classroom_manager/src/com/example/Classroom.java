package virtual_classroom_manager.src.com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Classroom {
    // Name of the classroom
    private String name;
    // ID of the teacher responsible for the classroom
    private String teacherIdString;
    // Set of student IDs enrolled in the classroom
    private Set<String> students = new HashSet<>();
    // Map to store assignment submissions, where the key is the assignment name and the value is a set of student IDs who submitted it
    private Map<String, Set<String>> assignmentSubmissions = new HashMap<>();

    // Constructor to initialize classroom with a name and teacher ID
    public Classroom(String name, String teacherIdString) {
        this.name = name;
        this.teacherIdString = teacherIdString;
    }

    // Check if the given teacher ID matches the classroom's teacher ID
    public boolean isSameTeacher(String teacherId) {
        return teacherIdString.equals(teacherId);
    }
    
    // Add a new assignment to the classroom
    public void addAssignment(String assignment) {
        assignmentSubmissions.put(assignment, new HashSet<>());
    }

    // List all assignments in the classroom
    public List<String> listAssignments() {
        return new ArrayList<>(assignmentSubmissions.keySet());
    }

    // Submit an assignment for a specific student
    public void submitAssignment(String assignment, String studentId) {
        System.out.println("Assignment " + assignment + " submitted by student " + studentId + " in classroom " + name);
        
        // Add the student ID to the set of submissions for this assignment
        assignmentSubmissions.get(assignment).add(studentId);
    }

    // Get the list of students who submitted a specific assignment
    public Set<String> submittedAssignmentList(String assignment) {
        return assignmentSubmissions.get(assignment);
    }

    // Check if a specific assignment exists in the classroom
    public boolean doesAssignmentExist(String assignment) {
        return assignmentSubmissions.containsKey(assignment);
    }
    
    // Add a student to the classroom
    public void addStudent(String studentId) {
        students.add(studentId);
    }

    // Check if a student is enrolled in the classroom
    public boolean isStudentEnrolled(String studentId) {
        return students.contains(studentId);
    }

    // List all students enrolled in the classroom
    public Set<String> listStudents() {
        return students;
    }
}
