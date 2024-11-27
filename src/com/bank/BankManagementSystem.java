package com.bank;

import java.util.Scanner;

public class BankManagementSystem {
    public static void main(String[] args) {
        BankOperations operations = new BankOperations();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Bank Management System ===");
            System.out.println("1. Create Account");
            System.out.println("2. View Account Details");
            System.out.println("3. Deposit Funds");
            System.out.println("4. Withdraw Funds");
            System.out.println("5. Close Account");
            System.out.println("6. Save Accounts to File");
            System.out.println("7. Load Accounts from File");
            System.out.println("8. Sort Accounts");
            System.out.println("9. Show Transaction History");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    operations.createAccount();
                    pause();
                }
                case 2 -> {
                    operations.viewAccountDetails();
                    pause();
                }
                case 3 -> {
                    operations.depositFunds();
                    pause();
                }
                case 4 -> {
                    operations.withdrawFunds();
                    pause();
                }
                case 5 -> {
                    operations.closeAccount();
                    pause();
                }
                case 6 -> {
                    operations.saveToFile();
                    pause();
                }
                case 7 -> {
                    operations.loadFromFile();
                    pause();
                }
                case 8 -> {
                    operations.sortAccounts();
                    pause();
                }
                case 9 -> {
                    System.out.print("Enter Account Number: ");
                    int accNumber = scanner.nextInt();
                    Account account = operations.getAccount(accNumber);
                    if (account != null) {
                        account.showTransactionHistory();
                    } else {
                        System.out.println("Account not found.");
                    }
                    pause();
                }
                case 10 -> System.out.println("Exiting system. Thank you!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 10);
    }

    // The pause method to wait for the user to press Enter
    private static void pause() {
        System.out.println("\nPress Enter to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
