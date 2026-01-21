package ui;

import dao.EnrollmentDAO;
import dao.StudentDAO;
import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class SearchStudentForm extends JFrame {


    private final JTextField txtId;
    private final JTextField txtName;
    private final JTextField txtEmail;
    private final JTextField txtDob;
    private final JButton btnSearch;
    private final JButton btnSaveGrade;

    private final DefaultTableModel gradeModel;
    private final JTable gradeTable;

    private final StudentDAO studentDAO = new StudentDAO();
    private final EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

    public SearchStudentForm() {
        setTitle("Search Student - View/Edit Grades");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // ---------------- TOP SEARCH PANEL ----------------
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("Student ID:"));
        txtId = new JTextField(15);
        top.add(txtId);

        btnSearch = new JButton("Search");
        top.add(btnSearch);

        add(top, BorderLayout.NORTH);

        // ---------------- STUDENT DETAILS PANEL ----------------
        JPanel details = new JPanel(new GridLayout(3, 2, 10, 10));
        details.setBorder(BorderFactory.createTitledBorder("Student Details"));

        details.add(new JLabel("Name:"));
        txtName = new JTextField();
        txtName.setEditable(false);
        details.add(txtName);

        details.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        txtEmail.setEditable(false);
        details.add(txtEmail);

        details.add(new JLabel("DOB:"));
        txtDob = new JTextField();
        txtDob.setEditable(false);
        details.add(txtDob);

        // ---------------- GRADES TABLE ----------------
        gradeModel = new DefaultTableModel(
                new String[]{"Course Code", "Course Title", "Semester", "Grade"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int col) {
                // Only grade is editable
                return col == 3;
            }
        };

        gradeTable = new JTable(gradeModel);
        JScrollPane gradesScroll = new JScrollPane(gradeTable);
        gradesScroll.setBorder(BorderFactory.createTitledBorder("Grades (Edit Grade Column Only)"));

        // Put details and grades in a center
        JPanel center = new JPanel(new BorderLayout(10, 10));
        center.add(details, BorderLayout.NORTH);
        center.add(gradesScroll, BorderLayout.CENTER);

        add(center, BorderLayout.CENTER);

        // ---------------- BOTTOM BUTTONS ----------------
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSaveGrade = new JButton("Save Selected Grade");
        btnSaveGrade.setEnabled(false);
        bottom.add(btnSaveGrade);

        add(bottom, BorderLayout.SOUTH);

        // ---------------- EVENTS ----------------
        btnSearch.addActionListener(_ -> doSearch());
        btnSaveGrade.addActionListener(_ -> saveSelectedGrade());

        setVisible(true);
    }

    private void doSearch() {
        String id = txtId.getText().trim();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Student ID.");
            return;
        }

        Student s = studentDAO.getStudentById(id);

        if (s == null) {
            JOptionPane.showMessageDialog(this, "Student not found!");
            clearStudentFields();
            gradeModel.setRowCount(0);
            btnSaveGrade.setEnabled(false);
            return;
        }

        // Show student details
        txtName.setText(s.getName());
        txtEmail.setText(s.getEmail());
        txtDob.setText(s.getDob());

        // Load grades only
        loadGrades(id);
        btnSaveGrade.setEnabled(true);
    }

    private void loadGrades(String studentId) {
        gradeModel.setRowCount(0);

        ArrayList<EnrollmentDAO.GradeRow> rows = enrollmentDAO.getGradesByStudentId(studentId);


        if (rows.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No grades found for this student (no enrolments recorded).");
            return;
        }

        for (EnrollmentDAO.GradeRow r : rows) {
            gradeModel.addRow(new Object[]{
                    r.courseCode(),
                    r.courseTitle(),
                    r.semester(),
                    r.grade()
            });
        }
    }

    private void saveSelectedGrade() {
        int row = gradeTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a course row first.");
            return;
        }

        String studentId = txtId.getText().trim();
        String courseCode = gradeModel.getValueAt(row, 0).toString();
        String semester = gradeModel.getValueAt(row, 2).toString(); // used in UPDATE
        Object gradeObj = gradeModel.getValueAt(row, 3);

        String grade = (gradeObj == null) ? "" : gradeObj.toString().trim();

        if (grade.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Grade cannot be empty.");
            return;
        }

        boolean ok = enrollmentDAO.updateGrade(studentId, courseCode, semester, grade);

        if (ok) {
            JOptionPane.showMessageDialog(this, "Grade updated successfully!");
            loadGrades(studentId); // refresh from DB
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update grade! Check if enrolment exists.");
        }
    }

    private void clearStudentFields() {
        txtName.setText("");
        txtEmail.setText("");
        txtDob.setText("");
    }
}
