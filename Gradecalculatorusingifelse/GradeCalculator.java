package gradeCalculator;

public class GradeCalculator {

	public static void main(String[] args) {
	
		        int score = 80; 

		        char grade; // Variable to store the letter grade

		        // Determine grade using if-else if-else statements
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
		            System.out.println("Invalid score! Please enter a value between 0 and 100.");
		            return; // Exit program early if invalid score
		        }

		        // Display result
		        System.out.println("Student Score: " + score);
		        System.out.println("Grade: " + grade);
		    }
		

	}


