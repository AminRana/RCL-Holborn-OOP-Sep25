package ses.models;

public class Student extends Person {

    private int age;

    public Student(String id, String name, int age) {
        super(id, name);
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String getDescription() {
        return "Student: " + getName() + " (ID: " + getId() + ")";
    }

    @Override
    public String toString() {
        return getId() + " - " + getName() + " (" + age + ")";
    }
}
