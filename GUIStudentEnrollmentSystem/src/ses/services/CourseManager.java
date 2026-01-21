package ses.services;

import ses.models.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseManager {

    // INSERT a new course into MySQL
    public void addCourse(String code, String title, int credits) {
        String sql = "INSERT INTO courses (course_code, title, credits) VALUES (?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, code);
            stmt.setString(2, title);
            stmt.setInt(3, credits);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // SELECT all courses from DB
    public List<Course> getAllCoursesFromDB() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT course_code, title, credits FROM courses";

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String code = rs.getString("course_code");
                String title = rs.getString("title");
                int credits = rs.getInt("credits");

                courses.add(new Course(code, title, credits));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }

    // FIND a course by code
    public Course findByCode(String code) {
        String sql = "SELECT course_code, title, credits FROM courses WHERE course_code = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, code);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Course(
                        rs.getString("course_code"),
                        rs.getString("title"),
                        rs.getInt("credits")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}

