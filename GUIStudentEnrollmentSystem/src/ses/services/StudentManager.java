package ses.services;

import ses.models.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {

    private final List<Student> students = new ArrayList<>();

    // Save a student into the database
    public void addStudentToDB(String id, String name, int age) {
        String sql = "INSERT INTO students (id, name, age) VALUES (?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            stmt.setString(2, name);
            stmt.setInt(3, age);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Load all students from DB into the in-memory list
    public void loadStudentsFromDB() {
        students.clear();

        String sql = "SELECT * FROM students";

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String id = rs.getString("id");      // matches your table
                String name = rs.getString("name");  // matches your table
                int age = rs.getInt("age");          // matches your table

                students.add(new Student(id, name, age));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Return the in-memory list
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    // ✅ NEW: wrapper used by your forms
    public List<Student> getAllStudentsFromDB() {
        loadStudentsFromDB();
        return getAllStudents();
    }
}

