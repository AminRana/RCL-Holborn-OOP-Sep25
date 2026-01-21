package ui;

import dao.EnrollmentDAO;

import javax.swing.*;

public class EnrollmentForm extends JFrame {

    private final JTextField studentIdField;
    private final JTextField courseCodeField;
    private final JTextField semesterField;

    public EnrollmentForm() {
        setTitle("Enroll Student");
        setSize(320, 260);                 // a bit wider
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel sidLabel = new JLabel("Student ID:");
        sidLabel.setBounds(10, 10, 100, 25);
        add(sidLabel);

        studentIdField = new JTextField();
        studentIdField.setBounds(120, 10, 170, 25);
        add(studentIdField);

        JLabel ccLabel = new JLabel("Course Code:");
        ccLabel.setBounds(10, 50, 100, 25);
        add(ccLabel);

        courseCodeField = new JTextField();
        courseCodeField.setBounds(120, 50, 170, 25);
        add(courseCodeField);

        JLabel semLabel = new JLabel("Semester:");
        semLabel.setBounds(10, 90, 100, 25);
        add(semLabel);

        semesterField = new JTextField();
        semesterField.setBounds(120, 90, 170, 25);
        add(semesterField);

        // ---- Buttons ----
        JButton btnEnroll = new JButton("Enroll");
        JButton btnUnenroll = new JButton("Unenroll");

        // Same size buttons
        int btnW = 120, btnH = 32;
        int y = 160;

        // left button
        btnEnroll.setBounds(20, y, btnW, btnH);

        // right button (frame width 320 -> right padding)
        btnUnenroll.setBounds(320 - 20 - btnW - 16, y, btnW, btnH); // -16 for window borders

        add(btnEnroll);
        add(btnUnenroll);

        // ---- Enroll action ----
        btnEnroll.addActionListener(_ -> {
            String studentId = studentIdField.getText().trim();
            String courseCode = courseCodeField.getText().trim();
            String semester = semesterField.getText().trim();

            if (studentId.isEmpty() || courseCode.isEmpty() || semester.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter Student ID, Course Code and Semester!");
                return;
            }

            // (You don't need Enrollment en unless your DAO uses it)
            boolean success = new EnrollmentDAO().enroll(studentId, courseCode, semester);

            JOptionPane.showMessageDialog(this,
                    success ? "Student Enrolled" : "Error Enrolling Student");
        });

        // ---- Unenroll action ----
        btnUnenroll.addActionListener(_ -> {
            String studentId = studentIdField.getText().trim();
            String courseCode = courseCodeField.getText().trim();
            String semester = semesterField.getText().trim();

            if (studentId.isEmpty() || courseCode.isEmpty() || semester.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter Student ID, Course Code and Semester first!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to UNENROLL this student?",
                    "Confirm Unenroll",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                boolean success = new EnrollmentDAO().unenroll(studentId, courseCode, semester);
                JOptionPane.showMessageDialog(this,
                        success ? "Student unenrolled successfully!" : "Unenroll failed! Record not found.");
            }
        });

        setVisible(true);
    }
}
