package ses.app;

import ses.models.Enrollment;
import ses.services.EnrollmentManager;
import ses.services.GradeManager;

import javax.swing.*;
import java.awt.*;

public class GradeForm extends JFrame {

    private final EnrollmentManager enrollmentManager;
    private final GradeManager gradeManager;

    private JComboBox<Enrollment> cmbEnrollments;
    private JTextField txtMarks;
    private JComboBox<String> cmbLetter;

    private JButton btnSave;
    private JButton btnClear;

    public GradeForm(EnrollmentManager em, GradeManager gm) {
        this.enrollmentManager = em;
        this.gradeManager = gm;

        initComponents();
        buildLayout();
        bindEvents();

        setTitle("Record Student Grade");
        setSize(420, 260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    // ---------------------------------------------------
    // 1. Create GUI components
    // ---------------------------------------------------
    private void initComponents() {

        cmbEnrollments = new JComboBox<>();
        enrollmentManager.getAllEnrollments().forEach(cmbEnrollments::addItem);

        txtMarks = new JTextField(20);

        cmbLetter = new JComboBox<>(new String[]{
                "A", "B", "C", "D", "E", "F"
        });

        btnSave = new JButton("Save Grade");
        btnClear = new JButton("Clear");

        Font font = new Font("Segoe UI", Font.PLAIN, 14);
        cmbEnrollments.setFont(font);
        txtMarks.setFont(font);
        cmbLetter.setFont(font);
        btnSave.setFont(font);
        btnClear.setFont(font);
    }

    // ---------------------------------------------------
    // 2. Layout UI components
    // ---------------------------------------------------
    private void buildLayout() {

        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        form.add(new JLabel("Enrollment:"));
        form.add(cmbEnrollments);

        form.add(new JLabel("Marks (0-100):"));
        form.add(txtMarks);

        form.add(new JLabel("Letter Grade:"));
        form.add(cmbLetter);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        buttons.add(btnSave);
        buttons.add(btnClear);

        setLayout(new BorderLayout());
        add(form, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
    }

    // ---------------------------------------------------
    // 3. Button Events (GUI-only)
    // ---------------------------------------------------
    private void bindEvents() {

        btnSave.addActionListener(e -> {

            Enrollment enrollment = (Enrollment) cmbEnrollments.getSelectedItem();
            String marksText = txtMarks.getText().trim();
            String letter = (String) cmbLetter.getSelectedItem();

            if (enrollment == null || marksText.isEmpty() || letter == null) {
                JOptionPane.showMessageDialog(this, "All fields are required.");
                return;
            }

            try {
                double marks = Double.parseDouble(marksText);

                if (marks < 0 || marks > 100) {
                    JOptionPane.showMessageDialog(this, "Marks must be between 0 and 100.");
                    return;
                }

                // GUI-only → call manager
                gradeManager.recordGrade(enrollment, marks, letter);

                JOptionPane.showMessageDialog(this,
                        "Grade recorded successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Marks must be numeric.");
            }
        });

        btnClear.addActionListener(e -> {
            if (cmbEnrollments.getItemCount() > 0)
                cmbEnrollments.setSelectedIndex(0);

            txtMarks.setText("");
            cmbLetter.setSelectedIndex(0);
        });
    }
}

