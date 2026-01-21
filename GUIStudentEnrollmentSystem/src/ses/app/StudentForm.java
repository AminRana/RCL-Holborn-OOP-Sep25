package ses.app;

import ses.services.StudentManager;

import javax.swing.*;
import java.awt.*;

public class StudentForm extends JFrame {

    private final StudentManager studentManager;

    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtAge;
    private JButton btnSave;
    private JButton btnClear;

    public StudentForm(StudentManager sm) {
        this.studentManager = sm;

        initComponents();
        buildLayout();
        bindEvents();

        setTitle("Register Student");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void initComponents() {
        txtId = new JTextField();
        txtName = new JTextField();
        txtAge = new JTextField();

        btnSave = new JButton("Save");
        btnClear = new JButton("Clear");
    }

    private void buildLayout() {
        setLayout(new GridLayout(4, 2, 5, 5));

        add(new JLabel("Student ID:"));
        add(txtId);

        add(new JLabel("Name:"));
        add(txtName);

        add(new JLabel("Age:"));
        add(txtAge);

        add(btnSave);
        add(btnClear);
    }

    private void bindEvents() {
        btnSave.addActionListener(e -> {
            String id = txtId.getText().trim();
            String name = txtName.getText().trim();
            String ageText = txtAge.getText().trim();

            if (id.isEmpty() || name.isEmpty() || ageText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required.");
                return;
            }

            try {
                int age = Integer.parseInt(ageText);
                studentManager.addStudentToDB(id, name, age);
                JOptionPane.showMessageDialog(this, "Student saved.");
                clearFields();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Age must be a number.");
            }
        });

        btnClear.addActionListener(e -> clearFields());
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtAge.setText("");
    }
}
