package model;

import java.util.LinkedList;

public class QuerySelector {
    String user = "user";
    String userPass = "user";
    String admin = "admin";
    String adminPass = "admin";

    public static LinkedList<String[]> userCourses(int id) {
        String query = String.format(
                "SELECT c.name, c.yhp, c.description FROM courses = c " +
                        "INNER JOIN students_courses = sc " +
                        "ON c.id = sc.courses_id " +
                        "WHERE sc.students_id = %d;", id);

        return MySQLConnector.selectQuery("root", "", query);
    }

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
}
