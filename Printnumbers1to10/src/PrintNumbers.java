import java.util.Scanner;

public class PrintNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) { // Loop keeps running until user types "exit"
            System.out.println("\nAvailable commands:");
            System.out.println("1. print numbers  → prints numbers from 1 to 10");
            System.out.println("2. even or odd    → choose to print even, odd, or both (1 to 50)");
            System.out.println("3. exit           → quit the program");
            System.out.print("\nType your command: ");
            command = scanner.nextLine();

            if (command.equalsIgnoreCase("print numbers")) {
                // Print numbers from 1 to 10
                System.out.println("\nNumbers from 1 to 10:");
                for (int i = 1; i <= 10; i++) {
                    System.out.println(i);
                }

            } else if (command.equalsIgnoreCase("even or odd")) {
                // Ask what type of numbers the user wants
                System.out.print("\nWould you like to print 'even', 'odd', or 'both'? ");
                String choice = scanner.nextLine();

                if (choice.equalsIgnoreCase("even")) {
                    System.out.println("\nEven numbers between 1 and 50:");
                    for (int i = 1; i <= 50; i++) {
                        if (i % 2 == 0) {
                            System.out.println(i);
                        }
                    }

                } else if (choice.equalsIgnoreCase("odd")) {
                    System.out.println("\nOdd numbers between 1 and 50:");
                    for (int i = 1; i <= 50; i++) {
                        if (i % 2 != 0) {
                            System.out.println(i);
                        }
                    }

                } else if (choice.equalsIgnoreCase("both")) {
                    System.out.println("\nEven and Odd numbers between 1 and 50:");
                    for (int i = 1; i <= 50; i++) {
                        if (i % 2 == 0) {
                            System.out.println(i + " is Even");
                        } else {
                            System.out.println(i + " is Odd");
                        }
                    }

                } else {
                    System.out.println("\nInvalid option. Please type 'even', 'odd', or 'both'.");
                }

            } else if (command.equalsIgnoreCase("exit")) {
                System.out.println("\nExiting program. Goodbye!");
                break;

            } else {
                System.out.println("\nInvalid command. Please try again.");
            }
        }

        scanner.close();
    }
}
