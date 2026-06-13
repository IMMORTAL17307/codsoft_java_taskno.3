package Task3_ATM;

import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            this.balance = 0.0;
        }
    }

    public double getBalance() {
        return balance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n=========================================");
            System.out.println("               ATM MENU                  ");
            System.out.println("=========================================");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");

            int choice = 0;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                scanner.nextLine();
                System.out.println("Invalid input. Please choose a number from 1 to 4.");
                continue;
            }

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    handleDepositMenu();
                    break;
                case 3:
                    handleWithdrawalMenu();
                    break;
                case 4:
                    System.out.println("\nThank you for using our ATM. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose a number from 1 to 4.");
            }
        }
    }

    public void checkBalance() {
        System.out.printf("\nYour current balance is: $%.2f\n", account.getBalance());
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Error: Deposit amount must be greater than zero.");
        } else if (account.deposit(amount)) {
            System.out.printf("Successfully deposited: $%.2f\n", amount);
            System.out.printf("New balance: $%.2f\n", account.getBalance());
        } else {
            System.out.println("Transaction failed. Please try again.");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Error: Withdrawal amount must be greater than zero.");
        } else if (amount > account.getBalance()) {
            System.out.println("Transaction failed: Insufficient balance in your account.");
            System.out.printf("Your current balance is: $%.2f\n", account.getBalance());
        } else if (account.withdraw(amount)) {
            System.out.printf("Successfully withdrew: $%.2f\n", amount);
            System.out.printf("Remaining balance: $%.2f\n", account.getBalance());
        } else {
            System.out.println("Transaction failed. Please try again.");
        }
    }

    private void handleDepositMenu() {
        System.out.print("\nEnter the amount to deposit: $");
        if (scanner.hasNextDouble()) {
            double amount = scanner.nextDouble();
            scanner.nextLine();
            deposit(amount);
        } else {
            System.out.println("Invalid amount. Transaction cancelled.");
            scanner.nextLine();
        }
    }

    private void handleWithdrawalMenu() {
        System.out.print("\nEnter the amount to withdraw: $");
        if (scanner.hasNextDouble()) {
            double amount = scanner.nextDouble();
            scanner.nextLine();
            withdraw(amount);
        } else {
            System.out.println("Invalid amount. Transaction cancelled.");
            scanner.nextLine();
        }
    }
}

public class ATMSystem {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.00);
        ATM atm = new ATM(userAccount);
        
        System.out.println("=========================================");
        System.out.println("        WELCOME TO YOUR BANK ATM         ");
        System.out.println("=========================================");
        
        atm.start();
    }
}
