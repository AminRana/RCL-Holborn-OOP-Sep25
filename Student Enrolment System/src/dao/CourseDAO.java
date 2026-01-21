package dao;

import database.DBConnection;
import model.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CourseDAO {

    public boolean addCourse(Course c) {
        String sql = "INSERT INTO courses(course_code, title, credits, semester) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getCourseCode());
            ps.setString(2, c.getTitle());
            ps.setInt(3, c.getCredits());
            ps.setString(4, c.getSemester());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
