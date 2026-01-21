package ui;

import db.DBConnection;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class SearchStudentPanel extends BackgroundPanel {

    private JTextField txtId;
    private JTextArea result;

    public SearchStudentPanel() {
        super("/resources/backgrounds/university.jpg");
        setLayout(null);

        JLabel lbl = new JLabel("Student ID:");
        lbl.setBounds(30, 30, 100, 25);
        lbl.setForeground(Color.BLACK);
        add(lbl);

        txtId = new JTextField();
        txtId.setBounds(140, 30, 150, 25);
        add(txtId);

        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(310, 30, 100, 25);
        btnSearch.setForeground(Color.BLACK);
        add(btnSearch);

        result = new JTextArea();
        result.setBounds(30, 80, 380, 150);
        result.setEditable(false);
        add(result);

        btnSearch.addActionListener(e -> searchStudent());
    }

    private void searchStudent() {
        String idText = txtId.getText().trim();

        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Student ID", "Validation Error", JOptionPane.ERROR_MESSAGE);
            result.setText("");
            return;
        }

        // Validate that the input is a number
        int studentId;
        try {
            studentId = Integer.parseInt(idText);
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Student ID must be a valid number!\nYou entered: \"" + idText + "\"", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            result.setText("");
            return;
        }

        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT * FROM student WHERE student_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result.setText(
                    "Student ID: " + rs.getInt("student_id") + "\n" +
                    "Name: " + rs.getString("name") + "\n" +
                    "Email: " + rs.getString("email") + "\n" +
                    "Programme: " + rs.getString("programme") + "\n" +
                    "Grades: " + rs.getDouble("grades")
                );
            } else {
                result.setText("Student not found");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            result.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            result.setText("");
        }
    }
}
