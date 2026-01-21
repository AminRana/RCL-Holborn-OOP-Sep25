package dao;

import database.DBConnection;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StudentDAO {

    private static final DateTimeFormatter UI_FMT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter MYSQL_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // UI dd-MM-yyyy  ->  MySQL yyyy-MM-dd
    private String toMysqlDate(String dobInput) {
        if (dobInput == null || dobInput.trim().isEmpty()) return null;

        dobInput = dobInput.trim();

        // if the user typed dd-MM-yyyy
        if (dobInput.matches("\\d{2}-\\d{2}-\\d{4}")) {
            LocalDate d = LocalDate.parse(dobInput, UI_FMT);
            return d.format(MYSQL_FMT);
        }

        // if the user typed yyyy-MM-dd (already MySQL style)
        if (dobInput.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return dobInput;
        }

        throw new IllegalArgumentException("DOB must be dd-MM-yyyy (example: 22-10-1982)");
    }

    // MySQL yyyy-MM-dd  ->  UI dd-MM-yyyy
    private String toUiDate(String dobFromDb) {
        if (dobFromDb == null || dobFromDb.trim().isEmpty()) return "";

        dobFromDb = dobFromDb.trim();

        // If DB already has dd-MM-yyyy (like 22-10-1982), return it
        if (dobFromDb.matches("\\d{2}-\\d{2}-\\d{4}")) {
            return dobFromDb;
        }

        // If DB has yyyy-MM-dd (like 1982-10-22), convert to dd-MM-yyyy
        if (dobFromDb.matches("\\d{4}-\\d{2}-\\d{2}")) {
            LocalDate d = LocalDate.parse(dobFromDb, MYSQL_FMT);
            return d.format(UI_FMT);
        }

        // unknown format, return raw text to avoid crash
        return dobFromDb;
    }

    // ✅ ADD
    public boolean addStudent(Student s) {
        String sql = "INSERT INTO students(student_id, name, email, dob) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection()) {
            assert conn != null;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, s.getStudentId());
                ps.setString(2, s.getName());
                ps.setString(3, s.getEmail());
                ps.setString(4, toMysqlDate(s.getDob()));   // dd-MM-yyyy -> yyyy-MM-dd

                return ps.executeUpdate() > 0;

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ GET BY ID
    public Student getStudentById(String id) {
        String sql = "SELECT student_id, name, email, dob FROM students WHERE student_id = ?";

        try (Connection conn = DBConnection.getConnection()) {
            assert conn != null;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, id);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return new Student(
                                rs.getString("student_id"),
                                rs.getString("name"),
                                rs.getString("email"),
                                toUiDate(rs.getString("dob"))   // show as dd-MM-yyyy
                        );
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // ✅ UPDATE
    public boolean updateStudent(Student s) {
        String sql = "UPDATE students SET name = ?, email = ?, dob = ? WHERE student_id = ?";

        try (Connection conn = DBConnection.getConnection()) {
            assert conn != null;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, s.getName());
                ps.setString(2, s.getEmail());
                ps.setString(3, toMysqlDate(s.getDob()));  // dd-MM-yyyy -> yyyy-MM-dd
                ps.setString(4, s.getStudentId());

                return ps.executeUpdate() > 0;

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ DELETE
    public boolean deleteStudent(String studentId) {
        String sql = "DELETE FROM students WHERE student_id = ?";

        try (Connection conn = DBConnection.getConnection()) {
            assert conn != null;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, studentId);
                return ps.executeUpdate() > 0;

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
