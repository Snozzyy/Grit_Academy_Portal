package model;

import java.util.LinkedList;

public class QuerySelector {
    String user = "root";
    String userPass = "";
    String admin = "root";
    String adminPass = "";

    public static LinkedList<String[]> logInStudent(String username, String password) {
        String query = String.format("SELECT id, fname, lname FROM students WHERE username = '%s' AND password = '%s';", username, password);
        return MySQLConnector.selectQuery("root", "", query);
    }

    public static LinkedList<String[]> logInTeacher(String username, String password) {
        String query = String.format("SELECT id, fname, privilege_type FROM teachers WHERE username = '%s' AND password = '%s';", username, password);
        return MySQLConnector.selectQuery("root", "", query);
    }

    // Show all courses for student
    public static LinkedList<String[]> userCourses(int id) {
        String query = String.format(
                "SELECT c.name, c.yhp, c.description FROM courses = c " +
                "INNER JOIN students_courses = sc " +
                "ON c.id = sc.courses_id " +
                "WHERE sc.students_id = %d;", id);

        return MySQLConnector.selectQuery("root", "", query);
    }

    // Show all students and teachers in course
    public static LinkedList<String[]> courseInfo(String course) {
        String query = String.format(
                "(SELECT s.fname, s.lname, 'student' AS role\n" +
                "FROM students = s\n" +
                "INNER JOIN students_courses = sc\n" +
                "ON s.id = sc.students_id\n" +
                "INNER JOIN courses = c\n" +
                "ON c.id = sc.courses_id\n" +
                "WHERE c.name = '%s')\n" +
                "UNION ALL\n" +
                "SELECT t.fname, t.lname, 'teacher' AS role\n" +
                "FROM teachers = t\n" +
                "INNER JOIN teachers_courses = tc\n" +
                "ON t.id = teachers_id\n" +
                "INNER JOIN courses = c\n" +
                "ON c.id = tc.courses_id\n" +
                "WHERE c.name = '%s';", course, course);

        return MySQLConnector.selectQuery("root", "", query);
    }

    // Show all courses
    public static LinkedList<String[]> allCourses() {
        String query = "SELECT name, yhp, description FROM courses;";
        return MySQLConnector.selectQuery("root", "", query);
    }

    // Show all students
    public static LinkedList<String[]> allStudents() {
        String query = "SELECT id, fname, lname, town, email, phone FROM students;";
        return MySQLConnector.selectQuery("root", "", query);
    }
}
