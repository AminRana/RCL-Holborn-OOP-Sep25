package ui;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class MainMenu extends JFrame {

    private final JLabel lblDateTime;

    public MainMenu() {
        setTitle("Student Enrolment System - A place for a better future");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // full screen
        setLayout(new BorderLayout());

        // ---------- HEADER (top banner) ----------
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        headerPanel.setBackground(new Color(245, 245, 245));

        // Left side: title and tagline
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        JLabel lblTitle = new JLabel("Student Enrolment System");
        lblTitle.setFont(new Font("Serif", Font.BOLD, 32));

        JLabel lblTaglineMain = new JLabel("Supporting Education Developing");
        lblTaglineMain.setFont(new Font("SansSerif", Font.ITALIC, 16));
        lblTaglineMain.setForeground(new Color(200, 0, 0));

        JLabel lblTaglineDev = new JLabel("Developed by Gustavo Martins");
        lblTaglineDev.setFont(new Font("SansSerif", Font.ITALIC, 10));
        lblTaglineDev.setForeground(new Color(0, 0, 0));

        titlePanel.add(lblTitle);
        titlePanel.add(Box.createVerticalStrut(5));
        titlePanel.add(lblTaglineMain);
        titlePanel.add(lblTaglineDev);

        // Right side: welcome + datetime
        JPanel rightPanel = new JPanel();
        rightPanel.setOpaque(false);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        JLabel lblWelcome = new JLabel("Welcome, Admin");
        lblWelcome.setAlignmentX(Component.RIGHT_ALIGNMENT);

        lblDateTime = new JLabel();
        lblDateTime.setAlignmentX(Component.RIGHT_ALIGNMENT);
        updateDateTime(); // set the initial date /time

        rightPanel.add(lblWelcome);
        rightPanel.add(Box.createVerticalStrut(5));
        rightPanel.add(lblDateTime);

        headerPanel.add(titlePanel, BorderLayout.WEST);
        headerPanel.add(rightPanel, BorderLayout.EAST);

        add(headerPanel, BorderLayout.NORTH);
// ---------- TOOLBAR (icons row) ----------
        JPanel toolbarPanel = getJPanel();


        // Helper method creates nice icon-style buttons
        JButton btnNewStudent = createIconButton("Add Student", "/icons/add_student.png");
        JButton btnSearchStudent = createIconButton("Search", "/icons/search.png");
        JButton btnViewAll = createIconButton("View All", "/icons/view_all.png");
        JButton btnAddCourse = createIconButton("Add Course", "/icons/add_course.png");
        JButton btnCourses = createIconButton("Courses", "/icons/courses.png");
        JButton btnEnroll = createIconButton("Enroll", "/icons/enroll.png");

        // Wire up actions to open your forms
        btnNewStudent.addActionListener(_ -> new StudentForm());
        btnAddCourse.addActionListener(_ -> new CourseForm());
        btnCourses.addActionListener(_ -> new CoursesWithStudentsView());
        btnEnroll.addActionListener(_ -> new EnrollmentForm());
        btnSearchStudent.addActionListener(_ -> new SearchStudentForm());
        btnViewAll.addActionListener(_ -> new ViewStudents());



        toolbarPanel.add(btnNewStudent);
        toolbarPanel.add(btnSearchStudent);
        toolbarPanel.add(btnViewAll);
        toolbarPanel.add(btnAddCourse);
        toolbarPanel.add(btnCourses);
        toolbarPanel.add(btnEnroll);

        add(toolbarPanel, BorderLayout.CENTER);




        pack();
        setVisible(true);
    }

    private static JPanel getJPanel() {
        JPanel toolbarPanel = new JPanel();
        toolbarPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50)); // top, left, bottom, right padding
        toolbarPanel.setBackground(new Color(126, 120, 120)); // light gray background
        toolbarPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 10)); // center icons and even spacing

// Add beautiful rounded panel effect
        toolbarPanel.setOpaque(true);
        toolbarPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        return toolbarPanel;
    }

    private JButton createIconButton(String text, String iconPath) {
        JButton btn = new JButton(text);

        // Icon + text alignments
        btn.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn.setHorizontalTextPosition(SwingConstants.CENTER);

        // Remove the ugly focus / border look
        btn.setFocusPainted(false);
        btn.setBorderPainted(true);
        btn.setContentAreaFilled(true);

        // Rounded border style
        btn.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 1, true));
        btn.setBackground(new Color(255, 255, 255)); // white background
        btn.setPreferredSize(new Dimension(150, 150));

        // Load icon safely
        try {
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(iconPath)));
            Image img = icon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            btn.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            System.out.println("Icon not found: " + iconPath);
        }

        return btn;
    }


    private void updateDateTime() {
        SimpleDateFormat fmt = new SimpleDateFormat("EEEE dd/MM/yyyy HH:mm:ss");
        lblDateTime.setText(fmt.format(new Date()));
    }
}
