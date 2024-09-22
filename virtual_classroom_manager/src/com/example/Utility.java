package virtual_classroom_manager.src.com.example;
public class Utility {

    public static boolean isTeacher(String userType) {
        return userType.equals("Teacher");
    }

    public static boolean isStudent(String userType) {
        return userType.equals("Student");
    }

    public static boolean doesClassroomExist(String className) {
        return VirtualClassRoomManager.classrooms.containsKey(className);
    }

    public static boolean doesStudentExistInClassroom(String studentId, String classroomName) {
        Classroom classroom = VirtualClassRoomManager.classrooms.get(classroomName);
        return classroom != null && classroom.isStudentEnrolled(studentId);
    }

    public static boolean doesAssignmentExistInClassroom(String assignmentName, String classroomName) {
        Classroom classroom = VirtualClassRoomManager.classrooms.get(classroomName);
        return classroom != null && classroom.doesAssignmentExist(assignmentName);
    }

    public static boolean isClassroomUnderSameTeacher(String teacherId , String classroomName){
        Classroom classroom = VirtualClassRoomManager.classrooms.get(classroomName);
        return classroom.isSameTeacher(teacherId);
    }
}
