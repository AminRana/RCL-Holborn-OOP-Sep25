package ses.app;

import ses.services.*;

import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame {

    private final StudentManager studentManager;
    private final CourseManager courseManager;
    private final EnrollmentManager enrollmentManager;
    private final GradeManager gradeManager;

    public MainGUI(StudentManager s, CourseManager c,
                   EnrollmentManager e, GradeManager g) {

        this.studentManager = s;
        this.courseManager = c;
        this.enrollmentManager = e;
        this.gradeManager = g;

        setTitle("Student Enrolment System - GUI");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1, 5, 5));
        setLocationRelativeTo(null);

        JButton btnRegisterStudent = new JButton("Register Student");
        JButton btnAddCourse = new JButton("Add Course");
        JButton btnEnrollStudent = new JButton("Enroll Student in Course");
        JButton btnRecordGrade = new JButton("Record Grade");
        JButton btnReports = new JButton("Reports");
        JButton btnExit = new JButton("Exit");

        add(btnRegisterStudent);
        add(btnAddCourse);
        add(btnEnrollStudent);
        add(btnRecordGrade);
        add(btnReports);
        add(btnExit);

        btnRegisterStudent.addActionListener(e1 -> new StudentForm(studentManager));
        btnAddCourse.addActionListener(e1 -> new CourseForm(courseManager));
        btnEnrollStudent.addActionListener(e1 ->
                new EnrollmentForm(studentManager, courseManager, enrollmentManager));
        btnRecordGrade.addActionListener(e1 ->
                new GradeForm(enrollmentManager, gradeManager));
        btnReports.addActionListener(e1 ->
                new ReportsWindow(studentManager, courseManager, enrollmentManager, gradeManager));
        btnExit.addActionListener(e1 -> System.exit(0));

        setVisible(true);
    }
}

