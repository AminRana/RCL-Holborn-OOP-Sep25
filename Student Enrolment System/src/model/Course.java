package model;

public class Course {
    private String courseCode;
    private String title;
    private int credits;
    private String semester;

    public Course(String code, String title, int credits, String semester) {
        this.courseCode = code;
        this.title = title;
        this.credits = credits;
        this.semester = semester;
    }

    public String getCourseCode() { return courseCode; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }
    public String getSemester() { return semester; }
}
