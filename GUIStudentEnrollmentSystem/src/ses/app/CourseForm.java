package ses.app;

import ses.services.CourseManager;

import javax.swing.*;
import java.awt.*;

public class CourseForm extends JFrame {

    private final CourseManager courseManager;

    private JTextField txtCode;
    private JTextField txtTitle;
    private JTextField txtCredits;
    private JButton btnSave;
    private JButton btnClear;

    public CourseForm(CourseManager cm) {
        this.courseManager = cm;

        initComponents();
        buildLayout();
        bindEvents();

        setTitle("Add Course");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void initComponents() {
        txtCode = new JTextField();
        txtTitle = new JTextField();
        txtCredits = new JTextField();
        btnSave = new JButton("Save");
        btnClear = new JButton("Clear");
    }

    private void buildLayout() {
        setLayout(new GridLayout(4, 2, 5, 5));

        add(new JLabel("Course Code:"));
        add(txtCode);

        add(new JLabel("Title:"));
        add(txtTitle);

        add(new JLabel("Credits:"));
        add(txtCredits);

        add(btnSave);
        add(btnClear);
    }

    private void bindEvents() {
        btnSave.addActionListener(e -> {
            String code = txtCode.getText().trim();
            String title = txtTitle.getText().trim();
            String creditsText = txtCredits.getText().trim();

            if (code.isEmpty() || title.isEmpty() || creditsText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required.");
                return;
            }

            try {
                int credits = Integer.parseInt(creditsText);
                courseManager.addCourse(code, title, credits);
                JOptionPane.showMessageDialog(this, "Course saved.");
                clearFields();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Credits must be a number.");
            }
        });

        btnClear.addActionListener(e -> clearFields());
    }

    private void clearFields() {
        txtCode.setText("");
        txtTitle.setText("");
        txtCredits.setText("");
    }
}
