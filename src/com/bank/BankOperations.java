package com.bank;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class BankOperations {
    private ArrayList<Account> accounts = new ArrayList<>();

    public void createAccount() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Account Number: ");
        int accountNumber = scanner.nextInt();
        if (getAccount(accountNumber) != null) {
            System.out.println("Error: Account number already exists!");
            return;
        }

        scanner.nextLine(); // Consume newline
        System.out.print("Enter Account Holder Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Account Type (Savings/Current): ");
        String type = scanner.nextLine();

        System.out.print("Enter Initial Deposit: ");
        double deposit = scanner.nextDouble();

        if (deposit < 0) {
            System.out.println("Initial deposit must be non-negative.");
            return;
        }

        accounts.add(new Account(accountNumber, name, type, deposit));
        System.out.println("Account created successfully!");
    }

    public void viewAccountDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Account Number: ");
        int accountNumber = scanner.nextInt();

        Account account = getAccount(accountNumber);
        if (account != null) {
            System.out.println("Account Details:");
            System.out.println("Account Number: " + account.getAccountNumber());
            System.out.println("Account Holder Name: " + account.getAccountHolderName());
            System.out.println("Account Type: " + account.getAccountType());
            System.out.println("Balance: " + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    public void depositFunds() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Account Number: ");
        int accountNumber = scanner.nextInt();

        Account account = getAccount(accountNumber);
        if (account != null) {
            System.out.print("Enter Deposit Amount: ");
            double amount = scanner.nextDouble();
            try {
                account.deposit(amount);
                System.out.println("Deposit successful! New Balance: " + account.getBalance());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    public void withdrawFunds() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Account Number: ");
        int accountNumber = scanner.nextInt();

        Account account = getAccount(accountNumber);
        if (account != null) {
            System.out.print("Enter Withdrawal Amount: ");
            double amount = scanner.nextDouble();
            try {
                account.withdraw(amount);
                System.out.println("Withdrawal successful! New Balance: " + account.getBalance());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    public void closeAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Account Number: ");
        int accountNumber = scanner.nextInt();

        Account account = getAccount(accountNumber);
        if (account != null) {
            accounts.remove(account);
            System.out.println("Account closed successfully.");
        } else {
            System.out.println("Account not found.");
        }
    }

    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("accounts.dat"))) {
            oos.writeObject(accounts);
            System.out.println("Accounts saved successfully.");
        } catch (Exception e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }

    public void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("accounts.dat"))) {
            accounts = (ArrayList<Account>) ois.readObject();
            System.out.println("Accounts loaded successfully.");
        } catch (Exception e) {
            System.out.println("No previous data found or error loading file.");
        }
    }

    public void sortAccounts() {
        System.out.println("1. Sort by Account Number");
        System.out.println("2. Sort by Name");
        System.out.println("3. Sort by Balance");
        System.out.print("Enter your choice: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1 -> accounts.sort(Comparator.comparingInt(Account::getAccountNumber));
            case 2 -> accounts.sort(Comparator.comparing(Account::getAccountHolderName));
            case 3 -> accounts.sort(Comparator.comparingDouble(Account::getBalance));
            default -> System.out.println("Invalid choice.");
        }

        System.out.println("Accounts sorted successfully.");
    }

    public Account getAccount(int accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }
}
