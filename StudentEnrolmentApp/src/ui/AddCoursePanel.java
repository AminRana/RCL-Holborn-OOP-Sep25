package ui;

import db.DBConnection;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AddCoursePanel extends BackgroundPanel {

    public AddCoursePanel() {
        super("/resources/backgrounds/university.jpg");
        setLayout(null);

        JTextField txtId = new JTextField();
        JTextField txtName = new JTextField();
        JTextField txtCredits = new JTextField();

        addLabel("Course ID", 30, 30);
        txtId.setBounds(140, 30, 150, 25);
        add(txtId);

        addLabel("Course Name", 30, 70);
        txtName.setBounds(140, 70, 150, 25);
        add(txtName);

        addLabel("Credits", 30, 110);
        txtCredits.setBounds(140, 110, 150, 25);
        add(txtCredits);

        JButton btn = new JButton("Save Course");
        btn.setBounds(140, 150, 150, 30);
        btn.setForeground(Color.BLACK);
        add(btn);

        btn.addActionListener(e -> {
            try {
                // Validate Course ID
                String idText = txtId.getText().trim();
                if (idText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter a Course ID", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int courseId;
                try {
                    courseId = Integer.parseInt(idText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Course ID must be a valid number!\nYou entered: \"" + idText + "\"", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validate Course Name
                if (txtName.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter a course name", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validate Credits
                String creditsText = txtCredits.getText().trim();
                if (creditsText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter credits", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int credits;
                try {
                    credits = Integer.parseInt(creditsText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Credits must be a valid number!\nYou entered: \"" + creditsText + "\"", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try (Connection con = DBConnection.getConnection()) {
                    PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO courses VALUES (?,?,?)"
                    );
                    ps.setInt(1, courseId);
                    ps.setString(2, txtName.getText().trim());
                    ps.setInt(3, credits);
                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Course Added Successfully!");

                    // Clear fields
                    txtId.setText("");
                    txtName.setText("");
                    txtCredits.setText("");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void addLabel(String text, int x, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setBounds(x, y, 100, 25);
        lbl.setForeground(Color.BLACK);
        add(lbl);
    }
}
