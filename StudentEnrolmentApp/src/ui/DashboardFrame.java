package ui;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    private JPanel contentPanel;

    public DashboardFrame() {
        setTitle("Student Enrolment System");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== LEFT MENU PANEL =====
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(new Color(45, 62, 80));
        menuPanel.setPreferredSize(new Dimension(230, 600));
        menuPanel.setLayout(new GridLayout(6, 1, 0, 15));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(30, 15, 30, 15));

        JButton btnAddStudent = createMenuButton(
                " Add Student",
                "/resources/icons/student.png"
        );

        JButton btnSearch = createMenuButton(
                " Search Student",
                "/resources/icons/search.png"
        );

        JButton btnView = createMenuButton(
                " View Students",
                "/resources/icons/search.png"
        );

        JButton btnCourse = createMenuButton(
                " Add Course",
                "/resources/icons/course.png"
        );

        JButton btnEnroll = createMenuButton(
                " Enroll Student",
                "/resources/icons/enroll.png"
        );

        JButton btnExit = createMenuButton(" Exit", null);

        menuPanel.add(btnAddStudent);
        menuPanel.add(btnSearch);
        menuPanel.add(btnView);
        menuPanel.add(btnCourse);
        menuPanel.add(btnEnroll);
        menuPanel.add(btnExit);

        // ===== CONTENT PANEL =====
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        JLabel welcome = new JLabel(
                "Welcome to Student Enrolment System",
                JLabel.CENTER
        );
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 24));
        welcome.setForeground(Color.BLACK);
        contentPanel.add(welcome, BorderLayout.CENTER);

        // ===== BUTTON ACTIONS =====
        btnAddStudent.addActionListener(e -> switchPanel(new AddStudentPanel()));
        btnSearch.addActionListener(e -> switchPanel(new SearchStudentPanel()));
        btnView.addActionListener(e -> switchPanel(new ViewStudentsPanel()));
        btnCourse.addActionListener(e -> switchPanel(new AddCoursePanel()));
        btnEnroll.addActionListener(e -> switchPanel(new EnrolStudentPanel()));
        btnExit.addActionListener(e -> System.exit(0));

        add(menuPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    // ===== PANEL SWITCHER =====
    private void switchPanel(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.add(panel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // ===== ICON LOADER (SCALED) =====
    private ImageIcon loadIcon(String path) {
        Image img = new ImageIcon(getClass().getResource(path)).getImage();
        Image scaled = img.getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    // ===== MENU BUTTON CREATOR =====
    private JButton createMenuButton(String text, String iconPath) {
        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setIconTextGap(12);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.BLACK);  // ✅ Mudado para PRETO para melhor legibilidade
        btn.setBackground(new Color(52, 152, 219));  // Azul claro
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(200, 45));
        btn.setOpaque(true);  // ✅ Garante que o background seja visível
        btn.setBorderPainted(true);  // ✅ Mostra a borda do botão

        if (iconPath != null) {
            btn.setIcon(loadIcon(iconPath));
        }

        return btn;
    }
}
