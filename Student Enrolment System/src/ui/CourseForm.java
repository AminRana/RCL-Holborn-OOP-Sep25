package ui;

import dao.CourseDAO;
import model.Course;
import javax.swing.*;

public class CourseForm extends JFrame {

    JTextField codeField, titleField, creditField, semesterField;

    public CourseForm() {
        setTitle("Add Course");
        setSize(300, 300);
        setLayout(null);

        JLabel codeLabel = new JLabel("Course Code:");
        codeLabel.setBounds(10, 10, 100, 25);
        add(codeLabel);

        codeField = new JTextField();
        codeField.setBounds(120, 10, 150, 25);
        add(codeField);

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setBounds(10, 50, 100, 25);
        add(titleLabel);

        titleField = new JTextField();
        titleField.setBounds(120, 50, 150, 25);
        add(titleField);

        JLabel creditLabel = new JLabel("Credits:");
        creditLabel.setBounds(10, 90, 100, 25);
        add(creditLabel);

        creditField = new JTextField();
        creditField.setBounds(120, 90, 150, 25);
        add(creditField);

        JLabel semLabel = new JLabel("Semester:");
        semLabel.setBounds(10, 130, 100, 25);
        add(semLabel);

        semesterField = new JTextField();
        semesterField.setBounds(120, 130, 150, 25);
        add(semesterField);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(100, 180, 100, 30);
        add(saveButton);

        saveButton.addActionListener(e -> {
            Course c = new Course(
                    codeField.getText(),
                    titleField.getText(),
                    Integer.parseInt(creditField.getText()),
                    semesterField.getText()
            );

            boolean success = new CourseDAO().addCourse(c);
            JOptionPane.showMessageDialog(this,
                    success ? "Course Added" : "Error Adding Course");
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
