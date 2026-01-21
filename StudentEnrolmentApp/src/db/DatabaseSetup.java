package db;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseSetup {

    public static void createTables() {
        try (Connection con = DBConnection.getConnection()) {
            if (con == null) {
                System.err.println("Failed to connect to database!");
                return;
            }

            Statement st = con.createStatement();

            // Create student table
            st.execute("""
                CREATE TABLE IF NOT EXISTS student (
                    student_id INT PRIMARY KEY,
                    name VARCHAR(100) NOT NULL,
                    email VARCHAR(100) NOT NULL,
                    programme VARCHAR(50) NOT NULL,
                    grades DOUBLE DEFAULT 0.0
                )
            """);

            // Create courses table
            st.execute("""
                CREATE TABLE IF NOT EXISTS courses (
                    id INT PRIMARY KEY,
                    name VARCHAR(100) NOT NULL,
                    credits INT NOT NULL
                )
            """);

            // Create enrolments table
            st.execute("""
                CREATE TABLE IF NOT EXISTS enrolments (
                    student_id INT NOT NULL,
                    course_id INT NOT NULL,
                    PRIMARY KEY (student_id, course_id),
                    FOREIGN KEY (student_id) REFERENCES student(student_id) ON DELETE CASCADE ON UPDATE CASCADE,
                    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE ON UPDATE CASCADE
                )
            """);

            System.out.println("Database tables created successfully!");

        } catch (Exception e) {
            System.err.println("Error creating tables: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
