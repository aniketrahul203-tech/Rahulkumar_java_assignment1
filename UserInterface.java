import java.util.Scanner;


class Account {
    private int accountNumber;
    private String accountHolderName;
    private double balance;
    private String email;
    private String phoneNumber;

    
    public Account(int accountNumber, String accountHolderName, double balance, String email, String phoneNumber) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

   
    public int getAccountNumber() {
        return accountNumber;
    }


    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("✅ Amount deposited successfully. New Balance: " + balance);
        } else {
            System.out.println("❌ Deposit amount must be positive!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("✅ Amount withdrawn successfully. New Balance: " + balance);
        } else if (amount > balance) {
            System.out.println("❌ Insufficient balance!");
        } else {
            System.out.println("❌ Withdrawal amount must be positive!");
        }
    }

    // Display account details
    public void displayAccountDetails() {
        System.out.println("\n--- Account Details ---");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: " + balance);
        System.out.println("Email: " + email);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("------------------------");
    }

    
    public void updateContactDetails(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        System.out.println("✅ Contact details updated successfully!");
    }
}


public class UserInterface {
    private static Account[] accounts = new Account[100]; 
    private static int accountCount = 0;                  
    private static int accountNumberSeed = 1001;          
    private static Scanner sc = new Scanner(System.in);


    public static void createAccount() {
        System.out.print("Enter account holder name: ");
        String name = sc.nextLine();

        System.out.print("Enter initial deposit amount: ");
        double amount = sc.nextDouble();
        sc.nextLine(); 

        System.out.print("Enter email address: ");
        String email = sc.nextLine();

        System.out.print("Enter phone number: ");
        String phone = sc.nextLine();

        accounts[accountCount] = new Account(accountNumberSeed, name, amount, email, phone);
        System.out.println("🎉 Account created successfully with Account Number: " + accountNumberSeed);

        accountNumberSeed++;
        accountCount++;
    }

   
    public static Account findAccount(int accNo) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber() == accNo) {
                return accounts[i];
            }
        }
        return null;
    }

  
    public static void performDeposit() {
        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();
        System.out.print("Enter deposit amount: ");
        double amount = sc.nextDouble();

        Account acc = findAccount(accNo);
        if (acc != null) {
            acc.deposit(amount);
        } else {
            System.out.println("❌ Account not found!");
        }
    }


    public static void performWithdrawal() {
        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();
        System.out.print("Enter withdrawal amount: ");
        double amount = sc.nextDouble();

        Account acc = findAccount(accNo);
        if (acc != null) {
            acc.withdraw(amount);
        } else {
            System.out.println("❌ Account not found!");
        }
    }

    public static void showAccountDetails() {
        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();

        Account acc = findAccount(accNo);
        if (acc != null) {
            acc.displayAccountDetails();
        } else {
            System.out.println("❌ Account not found!");
        }
    }

    public static void updateContact() {
        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();
        sc.nextLine(); 

        System.out.print("Enter new email: ");
        String email = sc.nextLine();
        System.out.print("Enter new phone number: ");
        String phone = sc.nextLine();

        Account acc = findAccount(accNo);
        if (acc != null) {
            acc.updateContactDetails(email, phone);
        } else {
            System.out.println("❌ Account not found!");
        }
    }

    
    public static void mainMenu() {
        int choice;
        do {
            System.out.println("\n===== Welcome to the Banking Application =====");
            System.out.println("1. Create a new account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. View account details");
            System.out.println("5. Update contact details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> performDeposit();
                case 3 -> performWithdrawal();
                case 4 -> showAccountDetails();
                case 5 -> updateContact();
                case 6 -> System.out.println("👋 Exiting... Thank you for using Banking App!");
                default -> System.out.println("❌ Invalid choice. Please try again!");
            }
        } while (choice != 6);
    }

    
    public static void main(String[] args) {
        mainMenu();
    }
}
