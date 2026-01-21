package ses.app;

import ses.models.*;
import ses.services.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ReportsWindow extends JFrame {

    private final StudentManager studentManager;
    private final CourseManager courseManager;
    private final EnrollmentManager enrollmentManager;
    private final GradeManager gradeManager;

    public ReportsWindow(StudentManager sm, CourseManager cm,
                         EnrollmentManager em, GradeManager gm) {
        this.studentManager = sm;
        this.courseManager = cm;
        this.enrollmentManager = em;
        this.gradeManager = gm;

        initGUI();
        setTitle("Reports - Student Enrollment System");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void initGUI() {
        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Students", createStudentsTable());
        tabs.add("Courses", createCoursesTable());
        tabs.add("Enrollments", createEnrollmentsTable());
        tabs.add("Grades", createGradesTable());
        add(tabs, BorderLayout.CENTER);
    }

    private JScrollPane createStudentsTable() {
        String[] cols = { "Student ID", "Name", "Age" };
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        JTable table = new JTable(model);

        for (Student s : studentManager.getAllStudentsFromDB()) {
            model.addRow(new Object[] { s.getId(), s.getName(), s.getAge() });
        }

        return new JScrollPane(table);
    }

    private JScrollPane createCoursesTable() {
        String[] cols = { "Course Code", "Title", "Credits" };
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        JTable table = new JTable(model);

        for (Course c : courseManager.getAllCoursesFromDB()) {
            model.addRow(new Object[] { c.getCourseCode(), c.getTitle(), c.getCredits() });
        }

        return new JScrollPane(table);
    }

    private JScrollPane createEnrollmentsTable() {
        String[] cols = { "Enrollment ID", "Student", "Course", "Semester" };
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        JTable table = new JTable(model);

        List<Enrollment> list = enrollmentManager.getAllEnrollments();
        for (Enrollment e : list) {
            model.addRow(new Object[] {
                    e.getId(),
                    e.getStudent().getName(),
                    e.getCourse().getCourseCode(),
                    e.getSemester()
            });
        }

        return new JScrollPane(table);
    }

    private JScrollPane createGradesTable() {
        String[] cols = { "Grade ID", "Student", "Course", "Marks", "Letter", "Semester" };
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        JTable table = new JTable(model);

        for (Grade g : gradeManager.getAllGrades()) {
            Enrollment e = g.getEnrollment();
            model.addRow(new Object[] {
                    g.getId(),
                    e.getStudent().getName(),
                    e.getCourse().getCourseCode(),
                    g.getMarks(),
                    g.getLetterGrade(),
                    e.getSemester()
            });
        }

        return new JScrollPane(table);
    }
}
