package ui;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import db.DBConnection;

public class AddStudentPanel extends BackgroundPanel {

    JTextField txtId, txtName, txtEmail, txtProgramme, txtGrades;
    JButton btnSave;

    public AddStudentPanel() {
        super("/resources/backgrounds/university.jpg");

        setLayout(new GridLayout(6, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel lblId = new JLabel("Student ID:");
        lblId.setForeground(Color.BLACK);
        add(lblId);
        txtId = new JTextField();
        add(txtId);

        JLabel lblName = new JLabel("Name:");
        lblName.setForeground(Color.BLACK);
        add(lblName);
        txtName = new JTextField();
        add(txtName);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setForeground(Color.BLACK);
        add(lblEmail);
        txtEmail = new JTextField();
        add(txtEmail);

        JLabel lblProgramme = new JLabel("Programme:");
        lblProgramme.setForeground(Color.BLACK);
        add(lblProgramme);
        txtProgramme = new JTextField();
        add(txtProgramme);

        JLabel lblGrades = new JLabel("Grades:");
        lblGrades.setForeground(Color.BLACK);
        add(lblGrades);
        txtGrades = new JTextField();
        add(txtGrades);

        btnSave = new JButton("Save Student");
        btnSave.setBackground(new Color(46, 204, 113));
        btnSave.setForeground(Color.BLACK);

        add(new JLabel()); // empty space
        add(btnSave);

        // ✅ ACTION LISTENER MUST BE INSIDE CONSTRUCTOR
        btnSave.addActionListener(e -> saveStudent());
    }

    // ✅ Proper method
    private void saveStudent() {
        try {
            // Validate Student ID is a number
            String idText = txtId.getText().trim();
            if (idText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a Student ID", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int studentId;
            try {
                studentId = Integer.parseInt(idText);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Student ID must be a valid number!\nYou entered: \"" + idText + "\"", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate other fields
            if (txtName.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a student name", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate grades
            double grades = 0.0;
            String gradesText = txtGrades.getText().trim();
            if (!gradesText.isEmpty()) {
                try {
                    grades = Double.parseDouble(gradesText);
                    if (grades < 0.0 || grades > 100.0) {
                        JOptionPane.showMessageDialog(this, "Grades must be between 0.0 and 100.0", "Validation Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Grades must be a valid number!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO student VALUES (?, ?, ?, ?, ?)"
            );

            ps.setInt(1, studentId);
            ps.setString(2, txtName.getText().trim());
            ps.setString(3, txtEmail.getText().trim());
            ps.setString(4, txtProgramme.getText().trim());
            ps.setDouble(5, grades);

            ps.executeUpdate();
            con.close();

            JOptionPane.showMessageDialog(this, "Student Added Successfully!");

            txtId.setText("");
            txtName.setText("");
            txtEmail.setText("");
            txtProgramme.setText("");
            txtGrades.setText("");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
