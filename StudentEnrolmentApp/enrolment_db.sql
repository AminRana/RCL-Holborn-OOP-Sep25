-- Create the database
CREATE DATABASE IF NOT EXISTS enrolment_db;
USE enrolment_db;

-- ========== STUDENT TABLE ==========
CREATE TABLE IF NOT EXISTS student (
    student_id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    programme VARCHAR(50) NOT NULL
);

-- ========== COURSES TABLE ==========
CREATE TABLE IF NOT EXISTS courses (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    credits INT NOT NULL
);

-- ========== ENROLMENTS TABLE ==========
DROP TABLE IF EXISTS enrolments;

CREATE TABLE IF NOT EXISTS enrolments (
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES student(student_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE ON UPDATE CASCADE
);



USE enrolment_db;

SHOW TABLES;



CREATE DATABASE IF NOT EXISTS enrolment_db;
USE enrolment_db;

DROP TABLE IF EXISTS enrolments;
DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS student;

CREATE TABLE student (
    student_id INT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    programme VARCHAR(50)
);

CREATE TABLE courses (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    credits INT
);

CREATE TABLE enrolments (
    student_id INT,
    course_id INT,
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES student(student_id),
    FOREIGN KEY (course_id) REFERENCES courses(id)
);


DESCRIBE enrolments;
