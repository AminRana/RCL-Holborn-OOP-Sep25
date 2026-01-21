package ses.services;

import ses.models.Course;
import ses.models.Enrollment;
import ses.models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentManager {

    private final List<Enrollment> enrollments = new ArrayList<>();

    // INSERT a student enrollment into MySQL
    public void enrollStudent(Student student, Course course, String semester) {
        String sql = "INSERT INTO enrollments (student_id, course_code, semester) VALUES (?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, student.getId());
            stmt.setString(2, course.getCourseCode());
            stmt.setString(3, semester);

            stmt.executeUpdate();

            // Get new enrollment ID
            ResultSet keys = stmt.getGeneratedKeys();
            int id = 0;
            if (keys.next()) {
                id = keys.getInt(1);
            }

            enrollments.add(new Enrollment(id, student, course, semester));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // LOAD all enrollments from DB
    public void loadEnrollmentsFromDB(StudentManager sm, CourseManager cm) {
        String sql = "SELECT * FROM enrollments";

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String studentId = rs.getString("student_id");
                String courseCode = rs.getString("course_code");
                String semester = rs.getString("semester");

                // ✅ FIX: use instance sm, not StudentManager class!
                Student student = sm.getAllStudentsFromDB().stream()
                        .filter(s -> s.getId().equals(studentId))
                        .findFirst().orElse(null);

                // Same fix for courses
                Course course = cm.getAllCoursesFromDB().stream()
                        .filter(c -> c.getCourseCode().equals(courseCode))
                        .findFirst().orElse(null);

                if (student != null && course != null) {
                    enrollments.add(new Enrollment(id, student, course, semester));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Enrollment> getAllEnrollments() {
        return new ArrayList<>(enrollments);
    }
}

