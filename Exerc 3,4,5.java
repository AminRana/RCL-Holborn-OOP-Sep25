package myPackage;

import java.util.Scanner;

public class Week4Consolidation {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice;

        System.out.println("=== Week 4: Consolidation Task ===");
        System.out.println("1. Factorial of a Number");
        System.out.println("2. Even or Odd Numbers (1 to 50)");
        System.out.println("3. Multiplication Table");
        System.out.print("Enter your choice (1-3): ");
        choice = input.nextInt();

        switch (choice) {
            case 1:
                factorial(input);
                break;
            case 2:
                evenOrOdd();
                break;
            case 3:
                multiplicationTable(input);
                break;
            default:
                System.out.println("Invalid choice. Please run the program again.");
        }

        input.close();
    }

    // ⿡ Factorial of a Number
    public static void factorial(Scanner input) {
        System.out.print("Enter a number to calculate its factorial: ");
        int num = input.nextInt();
        int factorial = 1;

        for (int i = 1; i <= num; i++) {
            factorial *= i;
        }

        System.out.println("Factorial of " + num + " is: " + factorial);
    }

    // ⿢ Even or Odd Numbers
    public static void evenOrOdd() {
        System.out.println("Even numbers between 1 and 50:");
        for (int i = 1; i <= 50; i++) {
            if (i % 2 == 0) {
                System.out.print(i + " ");
            }
        }

        System.out.println("\n\nOdd numbers between 1 and 50:");
        for (int i = 1; i <= 50; i++) {
            if (i % 2 != 0) {
                System.out.print(i + " ");
            }
        }

        System.out.println();
    }

    // ⿣ Multiplication Table
    public static void multiplicationTable(Scanner input) {
        System.out.print("Enter a number to print its multiplication table: ");
        int num = input.nextInt();

        System.out.println("\nMultiplication Table for " + num + ":");
        for (int i = 1; i <= 10; i++) {
            System.out.println(num + " x " + i + " = " + (num * i));
        }
    }
}