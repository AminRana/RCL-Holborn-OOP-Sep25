package ses.app;

import ses.models.Course;
import ses.models.Student;
import ses.services.CourseManager;
import ses.services.EnrollmentManager;
import ses.services.StudentManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EnrollmentForm extends JFrame {

    private final StudentManager studentManager;
    private final CourseManager courseManager;
    private final EnrollmentManager enrollmentManager;

    private JComboBox<Student> cmbStudents;
    private JComboBox<Course> cmbCourses;
    private JTextField txtSemester;
    private JButton btnEnroll;

    public EnrollmentForm(StudentManager s, CourseManager c, EnrollmentManager e) {
        this.studentManager = s;
        this.courseManager = c;
        this.enrollmentManager = e;

        initComponents();
        buildLayout();
        loadData();
        bindEvents();

        setTitle("Enroll Student in Course");
        setSize(420, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void initComponents() {
        cmbStudents = new JComboBox<>();
        cmbCourses = new JComboBox<>();
        txtSemester = new JTextField();
        btnEnroll = new JButton("Enroll");
    }

    private void buildLayout() {
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("Student:"));
        add(cmbStudents);

        add(new JLabel("Course:"));
        add(cmbCourses);

        add(new JLabel("Semester:"));
        add(txtSemester);

        add(new JLabel(""));
        add(btnEnroll);
    }

    private void loadData() {
        cmbStudents.removeAllItems();
        List<Student> students = studentManager.getAllStudentsFromDB();
        for (Student s : students) {
            cmbStudents.addItem(s);
        }

        cmbCourses.removeAllItems();
        List<Course> courses = courseManager.getAllCoursesFromDB();
        for (Course c : courses) {
            cmbCourses.addItem(c);
        }
    }

    private void bindEvents() {
        btnEnroll.addActionListener(e -> {
            Student student = (Student) cmbStudents.getSelectedItem();
            Course course = (Course) cmbCourses.getSelectedItem();
            String semester = txtSemester.getText().trim();

            if (student == null || course == null || semester.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required.");
                return;
            }

            enrollmentManager.enrollStudent(student, course, semester);
            JOptionPane.showMessageDialog(this, "Enrollment saved.");
            dispose();
        });
    }
}
