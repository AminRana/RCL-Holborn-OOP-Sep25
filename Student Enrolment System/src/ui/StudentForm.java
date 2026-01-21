package ui;

import dao.StudentDAO;
import model.Student;

import javax.swing.*;

public class StudentForm extends JFrame {

    JTextField idField, nameField, emailField, dobField;

    public StudentForm() {
        setTitle("Add Student");
        setSize(350, 300);
        setLayout(null);

        JLabel idLabel = new JLabel("Student ID:");
        idLabel.setBounds(10, 10, 120, 25);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(150, 10, 150, 25);
        add(idField);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(10, 50, 120, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 50, 150, 25);
        add(nameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 90, 120, 25);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(150, 90, 150, 25);
        add(emailField);

        JLabel dobLabel = new JLabel("Date of Birth (DD-MM-YYYY):");
        dobLabel.setBounds(10, 130, 200, 25);
        add(dobLabel);

        dobField = new JTextField();
        dobField.setBounds(210, 130, 90, 25);
        add(dobField);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(120, 180, 100, 30);
        add(saveButton);

        saveButton.addActionListener(e -> {
            Student s = new Student(
                    idField.getText(),
                    nameField.getText(),
                    emailField.getText(),
                    dobField.getText()        // NEW
            );

            boolean success = new StudentDAO().addStudent(s);

            JOptionPane.showMessageDialog(this,
                    success ? "Student added!" : "Failed to add student.");
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
