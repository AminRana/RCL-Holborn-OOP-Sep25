package model;

public class Student {

    private int studentId;
    private String name;
    private String email;
    private String programme;
    private double grades;

    public Student(int studentId, String name, String email, String programme, double grades) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.programme = programme;
        this.grades = grades;
    }

    public int getStudentId() { return studentId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getProgramme() { return programme; }
    public double getGrades() { return grades; }
    public void setGrades(double grades) { this.grades = grades; }
}
