package ui;

import dao.StudentDAO;
import database.DBConnection;
import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

public class ViewStudents extends JFrame {

    private final DefaultTableModel model;
    private final JTable table;
    private final StudentDAO studentDAO = new StudentDAO();

    public ViewStudents() {
        setTitle("All Students");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        model = new DefaultTableModel(new String[]{"ID", "Name", "Email", "DOB"}, 0) {
            public boolean isCellEditable(int row, int col) {
                return false; // no direct editing in the table
            }
        };

        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnEdit = new JButton("Edit");
        JButton btnDelete = new JButton("Delete");
        JButton btnRefresh = new JButton("Refresh");

        buttons.add(btnRefresh);
        buttons.add(btnEdit);
        buttons.add(btnDelete);

        add(buttons, BorderLayout.SOUTH);

        btnRefresh.addActionListener(_ -> loadStudents());

        btnEdit.addActionListener(_ -> editSelectedStudent());

        btnDelete.addActionListener(_ -> deleteSelectedStudent());

        loadStudents();
        setVisible(true);
    }

    private void loadStudents() {
        model.setRowCount(0);

        try (Connection conn = DBConnection.getConnection();
             Statement st = Objects.requireNonNull(conn).createStatement();
             ResultSet rs = st.executeQuery("SELECT student_id, name, email, dob FROM students")) {

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("student_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("dob")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading students!");
        }
    }

    private void editSelectedStudent() {
        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a student first!");
            return;
        }

        String id = model.getValueAt(row, 0).toString();
        String name = model.getValueAt(row, 1).toString();
        String email = model.getValueAt(row, 2).toString();
        String dob = model.getValueAt(row, 3).toString(); // currently yyyy-MM-dd from DB

        // Edit dialog fields
        JTextField txtName = new JTextField(name);
        JTextField txtEmail = new JTextField(email);
        JTextField txtDob = new JTextField(dob); // you can type yyyy-MM-dd or dd-MM-yyyy based on your DAO setup

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.add(new JLabel("Name:"));
        panel.add(txtName);
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);
        panel.add(new JLabel("DOB (dd-MM-yyyy):"));
        panel.add(txtDob);

        int option = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Edit Student: " + id,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (option == JOptionPane.OK_OPTION) {
            Student updated = new Student(
                    id,
                    txtName.getText().trim(),
                    txtEmail.getText().trim(),
                    txtDob.getText().trim()
            );

            boolean success = studentDAO.updateStudent(updated);

            if (success) {
                JOptionPane.showMessageDialog(this, "Student updated successfully!");
                loadStudents();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update student! Check DOB format.");
            }
        }
    }

    private void deleteSelectedStudent() {
        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a student first!");
            return;
        }

        String id = model.getValueAt(row, 0).toString();

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to DELETE student: " + id + "?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {

            boolean success = studentDAO.deleteStudent(id);

            if (success) {
                JOptionPane.showMessageDialog(this, "Student deleted successfully!");
                loadStudents();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete student! (Maybe student has enrolments)");
            }
        }
    }
}
