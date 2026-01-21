package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ViewStudentsPanel extends BackgroundPanel {

    JTable table;

    public ViewStudentsPanel() {
        super("/resources/backgrounds/university.jpg");
        setLayout(new java.awt.BorderLayout());

        table = new JTable();
        loadData();

        add(new JScrollPane(table), java.awt.BorderLayout.CENTER);
    }

    private void loadData() {
        DefaultTableModel model = new DefaultTableModel(
            new String[]{"ID", "Name", "Email", "Programme", "Grades"}, 0
        );

        try {
            Connection con = db.DBConnection.getConnection();

            if (con == null) {
                JOptionPane.showMessageDialog(this,
                    "Database connection failed!\n" +
                    "Please check:\n" +
                    "1. MySQL server is running\n" +
                    "2. Database 'enrolment_db' exists\n" +
                    "3. Username and password are correct",
                    "Connection Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM student");

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("student_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("programme"),
                    rs.getDouble("grades")
                });
            }

            table.setModel(model);
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                "Database Error: " + e.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error loading students: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
