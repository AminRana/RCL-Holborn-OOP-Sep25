package ses.models;

public class Course {
    private String courseCode;
    private String title;
    private int credits;

    public Course(String courseCode, String title, int credits) {
        this.courseCode = courseCode;
        this.title = title;
        this.credits = credits;
    }

    public String getCourseCode() { return courseCode; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }

    @Override
    public String toString() {
        return courseCode + " - " + title;
    }
}
