package ses.models;

public class Grade {

    private int id;
    private Enrollment enrollment;
    private double marks;
    private String letterGrade;

    // New grade not yet in DB
    public Grade(Enrollment enrollment, double marks, String letterGrade) {
        this(0, enrollment, marks, letterGrade);
    }

    // Grade loaded from DB
    public Grade(int id, Enrollment enrollment, double marks, String letterGrade) {
        this.id = id;
        this.enrollment = enrollment;
        this.marks = marks;
        this.letterGrade = letterGrade;
    }

    public int getId() { return id; }
    public Enrollment getEnrollment() { return enrollment; }
    public double getMarks() { return marks; }
    public String getLetterGrade() { return letterGrade; }

    @Override
    public String toString() {
        return enrollment.getStudent().getName() + " - " +
                enrollment.getCourse().getCourseCode() +
                " : " + marks + " (" + letterGrade + ")";
    }
}

