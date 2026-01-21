package ui;

import database.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CoursesWithStudentsView extends JFrame {

    private final DefaultTableModel coursesModel =
            new DefaultTableModel(new String[]{"Course Code", "Title", "Credits", "Semester"}, 0);

    private final DefaultTableModel studentsModel =
            new DefaultTableModel(new String[]{"Student ID", "Name", "Email", "DOB", "Enrol Semester"}, 0);

    private final JTable coursesTable = new JTable(coursesModel);

    private final JLabel lblSelectedCourse = new JLabel("Select a course to view enrolled students");

    public CoursesWithStudentsView() {
        setTitle("Courses - Enrolled Students");
        setSize(950, 550);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // ---------- TOP: Courses ----------
        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        topPanel.add(new JLabel("All Courses"), BorderLayout.NORTH);
        topPanel.add(new JScrollPane(coursesTable), BorderLayout.CENTER);

        // ---------- BOTTOM: Students in selected course ----------
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        bottomPanel.add(lblSelectedCourse, BorderLayout.NORTH);
        JTable studentsTable = new JTable(studentsModel);
        bottomPanel.add(new JScrollPane(studentsTable), BorderLayout.CENTER);

        // Split pane (top/bottom)
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPanel, bottomPanel);
        splitPane.setResizeWeight(0.5);
        add(splitPane, BorderLayout.CENTER);

        // Buttons
        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.addActionListener(_ -> {
            loadCourses();
            studentsModel.setRowCount(0);
            lblSelectedCourse.setText("Select a course to view enrolled students");
        });

        JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        south.add(btnRefresh);
        add(south, BorderLayout.SOUTH);

        // Click course -> load students
        coursesTable.getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) return;

            int row = coursesTable.getSelectedRow();
            if (row >= 0) {
                String courseCode = coursesModel.getValueAt(row, 0).toString();
                String title = coursesModel.getValueAt(row, 1).toString();
                lblSelectedCourse.setText("Students enrolled in: " + courseCode + " - " + title);

                loadStudentsForCourse(courseCode);
            }
        });

        loadCourses();
        setVisible(true);
    }

    private void loadCourses() {
        coursesModel.setRowCount(0);

        String sql = "SELECT course_code, title, credits, semester FROM courses";

        try (Connection conn = DBConnection.getConnection()) {
            assert conn != null;
            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    coursesModel.addRow(new Object[]{
                            rs.getString("course_code"),
                            rs.getString("title"),
                            rs.getInt("credits"),
                            rs.getString("semester")
                    });
                }

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading courses: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void loadStudentsForCourse(String courseCode) {
        studentsModel.setRowCount(0);

        String sql = """
        SELECT s.student_id, s.name, s.email, s.dob, e.semester
        FROM enrolments e
        JOIN students s ON e.student_id = s.student_id
        WHERE e.course_code = ?
        ORDER BY s.name
    """;

        try (Connection conn = DBConnection.getConnection()) {
            assert conn != null;
            try (PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, courseCode);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        studentsModel.addRow(new Object[]{
                                rs.getString("student_id"),
                                rs.getString("name"),
                                rs.getString("email"),
                                rs.getString("dob"),
                                rs.getString("semester")
                        });
                    }
                }

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error loading enrolled students: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}