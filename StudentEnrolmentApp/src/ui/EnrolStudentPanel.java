package ui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import db.DBConnection;

public class EnrolStudentPanel extends BackgroundPanel {

    public EnrolStudentPanel() {
        super("/resources/backgrounds/university.jpg");
        setLayout(null);

        JLabel lblStudent = new JLabel("Student ID:");
        lblStudent.setBounds(30, 30, 100, 25);
        lblStudent.setForeground(Color.BLACK);
        add(lblStudent);

        JTextField txtStudent = new JTextField();
        txtStudent.setBounds(140, 30, 150, 25);
        add(txtStudent);

        JLabel lblCourse = new JLabel("Course ID:");
        lblCourse.setBounds(30, 70, 100, 25);
        lblCourse.setForeground(Color.BLACK);
        add(lblCourse);

        JTextField txtCourse = new JTextField();
        txtCourse.setBounds(140, 70, 150, 25);
        add(txtCourse);

        JButton btnEnroll = new JButton("Enroll");
        btnEnroll.setBounds(140, 120, 120, 30);
        btnEnroll.setForeground(Color.BLACK);
        add(btnEnroll);

        btnEnroll.addActionListener(e -> {
            try {
                // Validate Student ID
                String studentIdText = txtStudent.getText().trim();
                if (studentIdText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter a Student ID", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int studentId;
                try {
                    studentId = Integer.parseInt(studentIdText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Student ID must be a valid number!\nYou entered: \"" + studentIdText + "\"", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validate Course ID
                String courseIdText = txtCourse.getText().trim();
                if (courseIdText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter a Course ID", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int courseId;
                try {
                    courseId = Integer.parseInt(courseIdText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Course ID must be a valid number!\nYou entered: \"" + courseIdText + "\"", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try (Connection con = DBConnection.getConnection()) {
                    PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO enrolments(student_id, course_id) VALUES (?, ?)"
                    );
                    ps.setInt(1, studentId);
                    ps.setInt(2, courseId);
                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(
                        this,
                        "Student Enrolled Successfully"
                    );

                    // Clear fields
                    txtStudent.setText("");
                    txtCourse.setText("");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
