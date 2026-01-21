package model;

public class Student {
    private String studentId;
    private String name;
    private String email;
    private String dob;

    public Student(String studentId, String name, String email, String dob) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getDob() { return dob; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setDob(String dob) { this.dob = dob; }
}
