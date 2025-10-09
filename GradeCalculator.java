public class GradeCalculator {
    public class SeasonFinder {

	}

	public static void main(String[] args) {
        // Predefined student score
        int score = 85; // You can change this value to test other scores
        char grade;

        // Grade determination using if-else if-else
        if (score >= 90 && score <= 100) {
            grade = 'A';
        } else if (score >= 80 && score <= 89) {
            grade = 'B';
        } else if (score >= 70 && score <= 79) {
            grade = 'C';
        } else if (score >= 60 && score <= 69) {
            grade = 'D';
        } else if (score >= 0 && score < 60) {
            grade = 'F';
        } else {
            System.out.println("Invalid score. Please enter a score between 0 and 100.");
            return;
        }

        // Output the result
        System.out.println("Student Score: " + score);
        System.out.println("Grade: " + grade);
    }
}