package ses.app;

import ses.services.*;

public class MainApp {
    public static void main(String[] args) {

        // Create managers
        StudentManager studentManager = new StudentManager();
        CourseManager courseManager = new CourseManager();
        EnrollmentManager enrollmentManager = new EnrollmentManager();
        GradeManager gradeManager = new GradeManager();


        // Start GUI
        new MainGUI(studentManager, courseManager, enrollmentManager, gradeManager);
    }
}

