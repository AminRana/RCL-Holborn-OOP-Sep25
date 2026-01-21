-- ========================================
-- Student Enrolment System Database Setup
-- ========================================

-- Create the database
CREATE DATABASE IF NOT EXISTS enrolment_db;

-- Use the database
USE enrolment_db;

-- Drop existing tables if they exist (to start fresh)
DROP TABLE IF EXISTS enrolments;
DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS student;

-- ========================================
-- Create STUDENT table
-- ========================================
CREATE TABLE student (
    student_id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    programme VARCHAR(50) NOT NULL
);

-- ========================================
-- Create COURSES table
-- ========================================
CREATE TABLE courses (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    credits INT NOT NULL
);

-- ========================================
-- Create ENROLMENTS table
-- ========================================
CREATE TABLE enrolments (
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES student(student_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ========================================
-- Insert Sample Data (Optional)
-- ========================================

-- Sample Students
INSERT INTO student (student_id, name, email, programme) VALUES
(1, 'John Doe', 'john.doe@example.com', 'Computer Science'),
(2, 'Jane Smith', 'jane.smith@example.com', 'Engineering'),
(3, 'Bob Johnson', 'bob.johnson@example.com', 'Business');

-- Sample Courses
INSERT INTO courses (id, name, credits) VALUES
(101, 'Introduction to Programming', 3),
(102, 'Database Systems', 4),
(103, 'Web Development', 3),
(104, 'Data Structures', 4),
(105, 'Software Engineering', 3);

-- Sample Enrolments
INSERT INTO enrolments (student_id, course_id) VALUES
(1, 101),
(1, 102),
(2, 101),
(2, 103),
(3, 104);

-- ========================================
-- Verify Setup
-- ========================================

-- Show all tables
SHOW TABLES;

-- Show student table structure
DESCRIBE student;

-- Show courses table structure
DESCRIBE courses;

-- Show enrolments table structure
DESCRIBE enrolments;

-- Display all data
SELECT 'Students:' AS '';
SELECT * FROM student;

SELECT 'Courses:' AS '';
SELECT * FROM courses;

SELECT 'Enrolments:' AS '';
SELECT * FROM enrolments;

-- ========================================
-- Success Message
-- ========================================
SELECT '✅ Database setup completed successfully!' AS 'Status';
