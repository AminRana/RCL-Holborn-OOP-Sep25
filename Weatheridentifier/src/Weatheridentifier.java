import java.util.Scanner;

public class Weatheridentifier {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Ask the user for the month number
        System.out.print("Enter month number (1-12): ");
        int month = input.nextInt();

        String season;

        // Determine season based on month
        switch (month) {
            case 12:
            case 1:
            case 2:
                season = "Winter";
                break;
            case 3:
            case 4:
            case 5:
                season = "Spring";
                break;
            case 6:
            case 7:
            case 8:
                season = "Summer";
                break;
            case 9:
            case 10:
            case 11:
                season = "Autumn";
                break;
            default:
                season = "Invalid month! Please enter a number between 1 and 12.";
                break;
        }

        // Display the result
        System.out.println("Season: " + season);
        
        input.close();
    }
}
