package ses.models;

public class Enrollment {

    private int id;
    private Student student;
    private Course course;
    private String semester;

    // For new enrollments before insert (no id yet)
    public Enrollment(Student student, Course course, String semester) {
        this(0, student, course, semester);
    }

    // For loading from the database (with id)
    public Enrollment(int id, Student student, Course course, String semester) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.semester = semester;
    }

    public int getId() { return id; }
    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
    public String getSemester() { return semester; }

    public void setId(int id) { this.id = id; }
    public void setStudent(Student student) { this.student = student; }
    public void setCourse(Course course) { this.course = course; }
    public void setSemester(String semester) { this.semester = semester; }

    @Override
    public String toString() {
        return student.getName() + " - " + course.getCourseCode() + " (" + semester + ")";
    }
}
