package ui;

import db.DBConnection;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class MainFrame extends JFrame {

    JTextField id, name, email, programme;
    JTextArea output;

    public MainFrame() {

        setTitle("Student Enrolment System");
        setSize(500, 400);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        id = new JTextField(10);
        name = new JTextField(10);
        email = new JTextField(10);
        programme = new JTextField(10);

        JButton saveBtn = new JButton("Save Student");
        saveBtn.setForeground(Color.BLACK);
        JButton viewBtn = new JButton("View Students");
        viewBtn.setForeground(Color.BLACK);

        output = new JTextArea(10, 40);

        JLabel lblId = new JLabel("Student ID");
        lblId.setForeground(Color.BLACK);
        add(lblId);
        add(id);
        JLabel lblName = new JLabel("Name");
        lblName.setForeground(Color.BLACK);
        add(lblName);
        add(name);
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setForeground(Color.BLACK);
        add(lblEmail);
        add(email);
        JLabel lblProgramme = new JLabel("Programme");
        lblProgramme.setForeground(Color.BLACK);
        add(lblProgramme);
        add(programme);

        add(saveBtn);
        add(viewBtn);
        add(new JScrollPane(output));

        saveBtn.addActionListener(e -> saveStudent());
        viewBtn.addActionListener(e -> viewStudents());
    }

    private void saveStudent() {
        try {
            // Validate Student ID
            String idText = id.getText().trim();
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
            if (name.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a student name", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Connection con = DBConnection.getConnection();

            if (con == null) {
                JOptionPane.showMessageDialog(this,
                    "Database connection failed!\n" +
                    "Please check:\n" +
                    "1. MySQL server is running\n" +
                    "2. Database 'enrolment_db' exists\n" +
                    "3. Username and password are correct",
                    "Connection Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            String sql = "INSERT INTO student VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, studentId);
            ps.setString(2, name.getText().trim());
            ps.setString(3, email.getText().trim());
            ps.setString(4, programme.getText().trim());
            ps.executeUpdate();

            con.close();

            JOptionPane.showMessageDialog(this, "Student Saved!");

            // Clear fields
            id.setText("");
            name.setText("");
            email.setText("");
            programme.setText("");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error saving student: " + e.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void viewStudents() {
        try {
            Connection con = DBConnection.getConnection();

            if (con == null) {
                JOptionPane.showMessageDialog(this,
                    "Database connection failed!\n" +
                    "Please check:\n" +
                    "1. MySQL server is running\n" +
                    "2. Database 'enrolment_db' exists\n" +
                    "3. Username and password are correct",
                    "Connection Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM student");

            output.setText("");
            while (rs.next()) {
                output.append(
                    rs.getInt("student_id") + " | " +
                    rs.getString("name") + " | " +
                    rs.getString("email") + " | " +
                    rs.getString("programme") + "\n"
                );
            }

            con.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error loading students: " + e.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        db.DatabaseSetup.createTables();
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }
}
