import java.util.Scanner;

public class FactorialCalculatorLoop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) { // Infinite loop until user chooses to stop
            System.out.print("\nEnter a number to find its factorial: ");
            int number = scanner.nextInt();
            long factorial = 1;

            if (number < 0) {
                System.out.println("❌ Factorial is not defined for negative numbers.");
            } else {
                for (int i = 1; i <= number; i++) {
                    factorial *= i;
                }
                System.out.println("✅ The factorial of " + number + " is: " + factorial);
            }

            // Ask user if they want to continue
            System.out.print("\nDo you want to calculate another factorial? (yes/no): ");
            String choice = scanner.next();

            if (choice.equalsIgnoreCase("no")) {
                System.out.println("\nExiting program. Goodbye!");
                break; // Exit the loop
            }
        }

        scanner.close();
    }
}
