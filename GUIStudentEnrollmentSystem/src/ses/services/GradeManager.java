package ses.services;

import ses.models.Enrollment;
import ses.models.Grade;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradeManager {

    private final List<Grade> grades = new ArrayList<>();

    // INSERT a grade into DB
    public void recordGrade(Enrollment enrollment, double marks, String letterGrade) {
        String sql = "INSERT INTO grades (enrollment_id, marks, letter_grade) VALUES (?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, enrollment.getId());
            stmt.setDouble(2, marks);
            stmt.setString(3, letterGrade);

            stmt.executeUpdate();

            // Get new grade ID
            ResultSet keys = stmt.getGeneratedKeys();
            int id = 0;
            if (keys.next()) {
                id = keys.getInt(1);
            }

            grades.add(new Grade(id, enrollment, marks, letterGrade));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // LOAD ALL grades
    public void loadGradesFromDB(EnrollmentManager em) {
        String sql = "SELECT id, enrollment_id, marks, letter_grade FROM grades";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int enrollmentId = rs.getInt("enrollment_id");
                double marks = rs.getDouble("marks");
                String letter = rs.getString("letter_grade");

                Enrollment enrollment = em.getAllEnrollments().stream()
                        .filter(e -> e.getId() == enrollmentId)
                        .findFirst().orElse(null);

                if (enrollment != null) {
                    grades.add(new Grade(id, enrollment, marks, letter));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Grade> getAllGrades() {
        return new ArrayList<>(grades);
    }
}
