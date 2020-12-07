package atm;

import java.util.Scanner;
/**
 *
 * @author David A-T
 * 
 */
public class ATM {
    private String atmId;
    private static int nextId;

    public ATM(){
        this.atmId = String.format("%06d", nextId++);
    }
    
    public void pipeline(){
        printWelcome();
        User user = readUserId();
        if (!inputPassword(user))
            System.exit(2);
        
        Account account = chooseAccount(user);
        int oper = chooseOperation();
        switch (oper){
            case 1:
                withdraw(account);
                break;
            case 2:
                deposit(account);
                break;
            case 3:
                displayBalance(account);
        }
        
        printGoodBye();
    }
    
    public void printWelcome(){
        System.out.println("Welcome to use out ATM");
    }
    
    public User readUserId(){
        Scanner console = new Scanner(System.in);
        
        System.out.println("Please enter your user ID");
        String inputId = console.next();
        
        for (int i = 0; i < Bank.getUsers().size(); i++)
            if (inputId.equals(Bank.getUsers().get(i).getUserId()))
                return Bank.getUsers().get(i);
        
        System.out.println("Your ID is invalid. Goodbye");
        System.exit(1);
        return null;
    }
    
    public boolean inputPassword(User user){
        Scanner console = new Scanner(System.in);
        int maxTry = 3;
        
        for (int i = 0; i < maxTry; i++){
            System.out.println("Please enter your password");
            String password = console.next();
            if (user.getPassword().equals(password))
                return true;
            System.out.println("Your password is wrong");
        }
        System.out.println("Your input password wrong for 3 times");
        System.exit(2);
        return false;
    }
    
    public Account chooseAccount(User user){
        Scanner console = new Scanner(System.in);
        
        System.out.println("Please choose the account you want to operate with:"
                + "\n\t1. Chequing Account"
                + "\n\t2. Saving Account");
        int accountChoice = console.nextInt();
        
        return accountChoice == 1 ? user.getChequingAccount() : user.getSavingAccount();
    }
    
    public int chooseOperation(){
        Scanner console = new Scanner(System.in);
        
        System.out.println("Please choose the operation:"
                + "\n\t1. Withdraw"
                + "\n\t2. Deposit"
                + "\n\t3. Display Balance");
        int operation = console.nextInt();
        
        return operation;
    }
    
    public boolean withdraw(Account account){
        Scanner console = new Scanner(System.in);
        
        System.out.println("How much do you want to withdraw?");
        double amount = console.nextDouble();
        
        if (account.getBalance() < amount){
            System.out.println("Sorry, you don't have enough balance");
            return false;
        }
        account.setBalance(account.getBalance() - amount);
        System.out.println("Withdraw Successful");
        return true;
    }
    
    public boolean deposit(Account account){
        Scanner console = new Scanner(System.in);
        
        System.out.println("How much do you want to deposit?");
        double amount = console.nextDouble();

        account.setBalance(account.getBalance() + amount);
        System.out.println("Deposit Successful");
        return true;
    }
    
    public void displayBalance(Account account){
        System.out.printf("Your current balance is $%.2f\n", account.getBalance());
    }
    
    public void printGoodBye(){
        System.out.println("Goodbye, See you later.");
    }
    
    public boolean equals(ATM atm){
        return this.atmId.equals(atm.atmId);
    }

    @Override
    public String toString(){
        String str = "";
        
        str += String.format("%-10s: %s", "ATM ID", atmId);
        
        return str;
    }
}
