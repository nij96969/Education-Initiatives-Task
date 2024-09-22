package virtual_classroom_manager.src.com.example;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Classroom {
    
    private String name;
    private String teacherIdString;
    private Set<String> students = new HashSet<>();
    private Map<String, Set<String>> assignmentSubmissions = new HashMap<>();

    public Classroom(String name , String teacherIdString) {
        this.name = name;
        this.teacherIdString = teacherIdString;
    }

    public boolean isSameTeacher(String teacherId){
        return teacherIdString.equals(teacherId);
    }

    public boolean isMatchTeacherId(String teacherIdString){
        return teacherIdString.equals(this.teacherIdString);
    }
    
    public void addAssignment(String assignment) {
        assignmentSubmissions.put(assignment, new HashSet<>());
    }

    public List<String> listAssignments() {
        return new ArrayList<>(assignmentSubmissions.keySet());
    }

    public void submitAssignment(String assignment, String studentId) {
        System.out.println("Assignment " + assignment + " submitted by student " + studentId + " in classroom " + name);
        
        // Add the student ID to the set of submissions for this assignment
        assignmentSubmissions.get(assignment).add(studentId);
    }

    public Set<String> submittedAssignmentList(String assignment){
        return assignmentSubmissions.get(assignment);
    }

    public boolean doesAssignmentExist(String assignment) {
        return assignmentSubmissions.containsKey(assignment);
    }
    
    public void addStudent(String studentId) {
        students.add(studentId);
    }

    public boolean isStudentEnrolled(String studentId) {
        return students.contains(studentId);
    }

    public Set<String> listStudents() {
        return students;
    }
}
