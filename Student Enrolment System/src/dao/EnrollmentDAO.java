package dao;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EnrollmentDAO {

    // ---------- Small helper class used by SearchStudentForm ----------
        public record GradeRow(String courseCode, String courseTitle, String semester, String grade, String status) {
    }
    // ---------- ENROLL ----------
    public boolean enroll(String studentId, String courseCode, String semester) {
        String sql = "INSERT INTO enrolments(student_id, course_code, semester, grade, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection()) {
            assert conn != null;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, studentId);
                ps.setString(2, courseCode);
                ps.setString(3, semester);
                ps.setString(4, "");          // default grade
                ps.setString(5, "Enrolled");  // default status

                return ps.executeUpdate() > 0;

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // ---------- UNENROLL (DELETE ENROLMENT) ----------
    public boolean unenroll(String studentId, String courseCode, String semester) {
        String sql = "DELETE FROM enrolments WHERE student_id = ? AND course_code = ? AND semester = ?";

        try (Connection conn = DBConnection.getConnection()) {
            assert conn != null;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, studentId);
                ps.setString(2, courseCode);
                ps.setString(3, semester);

                return ps.executeUpdate() > 0;

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // ---------- GET GRADES BY STUDENT (for SearchStudentForm table) ----------
    public ArrayList<GradeRow> getGradesByStudentId(String studentId) {
        ArrayList<GradeRow> list = new ArrayList<>();

        // Join courses to show the course title (if your course table is named "courses")
        String sql =
                "SELECT e.course_code, c.title, e.semester, e.grade, e.status " +
                        "FROM enrolments e " +
                        "LEFT JOIN courses c ON c.course_code = e.course_code " +
                        "WHERE e.student_id = ? " +
                        "ORDER BY e.semester, e.course_code";

        try (Connection conn = DBConnection.getConnection()) {
            assert conn != null;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, studentId);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        String courseCode = rs.getString("course_code");
                        String title = rs.getString("title"); // can be null if no match in courses
                        String semester = rs.getString("semester");
                        String grade = rs.getString("grade");
                        String status = rs.getString("status");

                        list.add(new GradeRow(courseCode, title, semester, grade, status));
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    // ---------- UPDATE GRADE (edit grade only) ----------
    public boolean updateGrade(String studentId, String courseCode, String semester, String newGrade) {
        String sql = "UPDATE enrolments SET grade = ? WHERE student_id = ? AND course_code = ? AND semester = ?";

        try (Connection conn = DBConnection.getConnection()) {
            assert conn != null;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, newGrade);
                ps.setString(2, studentId);
                ps.setString(3, courseCode);
                ps.setString(4, semester);

                return ps.executeUpdate() > 0;

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
