import java.util.Scanner;

public class MultiplicationTableLoop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) { // Keep asking until user chooses to stop
            System.out.print("\nEnter a number to print its multiplication table: ");
            int number = scanner.nextInt();

            System.out.println("\nMultiplication Table of " + number + ":");
            for (int i = 1; i <= 10; i++) {
                System.out.println(number + " x " + i + " = " + (number * i));
            }

            // Ask if the user wants to do another one
            System.out.print("\nDo you want to print another table? (yes/no): ");
            String choice = scanner.next();

            if (choice.equalsIgnoreCase("no")) {
                System.out.println("\nExiting program. Goodbye!");
                break; // Exit the loop
            }
        }

        scanner.close();
    }
}
