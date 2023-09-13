import java.util.HashMap; 
import java.util.Scanner; 
class Account { 
    private String name; 
    private Scanner input; 
    private String userId; 
    private int pin; 
    private String accountNumber; 
    private HashMap<String, Integer> details; 
    private HashMap<String, String> detailsName; 
    private HashMap<String, Integer> detailsBalance; 
    private HashMap<String, String> detailsAccountNumber; 
    private HashMap<String, String> transactionHistory; 
    private String checkUserId; private int checkPin; 
    Account() { 
        input = new Scanner(System.in); 
        details = new HashMap<>(); 
        detailsName = new HashMap<>(); 
        detailsBalance = new HashMap<>(); 
        detailsAccountNumber = new HashMap<>(); 
        transactionHistory = new HashMap<>(); 
    } 
    public void register() { 
        System.out.println("Please Enter your name : "); 
        name = input.nextLine(); 
        while (true) { 
            System.out.println("Please Enter your UserId : "); 
            userId =  input.nextLine();
            if (!details.containsKey(userId)) {
                break;
            } else {
                System.out.println("Please Enter valid userId");
            }
        }
        while (true) {
            System.out.println("please Enter your Account Number : ");
            accountNumber = input.nextLine();
            if (!details.containsKey(accountNumber) && accountNumber != name && accountNumber != userId) {
                break;
            } else {
                System.out.println("Please Enter valid Account Number");
            }
        }

        while (true) {
            System.out.println("Please Enter your pin : ");
            pin = input.nextInt();
            if (pin >= 1000) {
                break;
            } else {
                System.out.println("your pin should be of 4 digits");
            }
        }

        details.put(userId, pin);
        detailsName.put(userId, name);
        detailsAccountNumber.put(accountNumber, userId);
        input.nextLine();
    }

    public void logIn() {
        Boolean exit = true;
        int attempts = 0;

        while (exit) {

            System.out.println("Please Enter your UserId : ");
            checkUserId = input.nextLine();
            attempts++;
            if (!details.containsKey(checkUserId)) {
                if (attempts == 3) {
                    System.out.println("Many Attempts!");
                }
                System.out.println("Please Enter A valid UserId!");
            } else {
                exit = false;
            }
        }
        exit = true;
        attempts = 0;
        while (exit) {
            System.out.println("Please Enter your pin : ");
            checkPin = input.nextInt();
            attempts++;
            if (details.get(checkUserId) != checkPin) {
                if (attempts == 3) {
                    System.out.println("Many Attempts!");
                }
                System.out.println("Please Enter A valid Pin ");
            } else {
                exit = false;
                System.out.println("Hello " + this.getName(checkUserId));
            }
        }

    }

    String getName(String id) {
        return detailsName.get(id);
    }

    public void withdraw() {
        System.out.println("Please Enter amount to Withdraw");
        int amount = input.nextInt();
        if (detailsBalance.get(checkUserId) != null && amount <= detailsBalance.get(checkUserId)) {
            detailsBalance.put(checkUserId, detailsBalance.get(checkUserId) - amount);

            String his = amount + "   withdraw from your acccount\n";
            if (transactionHistory.get(checkUserId) == null) {
                transactionHistory.put(checkUserId, his);
            } else {
                his = transactionHistory.get(checkUserId) + his;

transactionHistory.put(checkUserId, his);
            }
            System.out.println(amount + "  successfully withdraw from your acccount  ");
        } else {
            System.out.println("Balance is not Sufficient");
        }
    }

    public void deposit() {
        System.out.println("Please Enter amount to Deposit");
        int amount = input.nextInt();
        String his = amount + "   Deposited to your acccount\n";

        if (transactionHistory.get(checkUserId) == null) {
            transactionHistory.put(checkUserId, his);
        } else {
            his = transactionHistory.get(checkUserId) + his;
            transactionHistory.put(checkUserId, his);
        }
        System.out.println(amount + "  successfully Deposited to your acccount  ");
        if (detailsBalance.get(checkUserId) != null) {

            amount += detailsBalance.get(checkUserId);
        }
        detailsBalance.put(checkUserId, amount);

    }

    public void transfer() {
        System.out.println("Please Enter account Number to transfer the amount");
        String accountNumber = input.nextLine();
        if (!detailsAccountNumber.containsKey(accountNumber)) {
            System.out.println("Please Enter correct Account Number");
        } else {
            System.out.println("please enter the amount :");
            int transferAmount = input.nextInt();
            int receivedAmount = transferAmount;

            if (transferAmount <= detailsBalance.get(checkUserId) && detailsBalance.get(checkUserId) != null) {
                detailsBalance.put(checkUserId, detailsBalance.get(checkUserId) - transferAmount);
                String his = transferAmount + "   Transferred from your acccount\n";

                if (transactionHistory.get(checkUserId) == null) {
                    transactionHistory.put(checkUserId, his);
                } else {
                    his = transactionHistory.get(checkUserId) + his;
                    transactionHistory.put(checkUserId, his);
                }
                System.out.println(transferAmount + "  successfully Transfered from your acccount  ");
            } else {
                System.out.println("Balance is not Sufficient");
            }
            String id = detailsAccountNumber.get(accountNumber);
            if (detailsBalance.get(id) != null) {
                transferAmount += detailsBalance.get(checkUserId);
            }

            detailsBalance.put(id, transferAmount);
            String his = receivedAmount + "   Received by your acccount\n";

            if (transactionHistory.get(id) == null) {
                transactionHistory.put(id, his);
            } else {
                his = transactionHistory.get(id) + his;
                transactionHistory.put(id, his);
            }

        }
    }

    public void transactionHistory() {
        System.out.println("Your  Transaction History will show below : ");
        String history = transactionHistory.get(checkUserId);
        System.out.println(history);
    }
}

public class ATM {
    public static void main(String atul[]) {
        Account myAccount = new Account();
        Scanner input = new Scanner(System.in);
        int registerOrNot;

        while (true) {
            System.out.println("Enter 1 for registration");
            System.out.println("Enter 2 for logIn");
            registerOrNot = input.nextInt();
            if (registerOrNot == 1) {
                myAccount.register();
            } else if (registerOrNot == 2) {
                myAccount.logIn();
            } else {
                break;
            }
            boolean exit = true;
            while (exit) {
                System.out.println("1.Transaction History");
                System.out.println("2.Withdraw");

System.out.println("3.Deposit");
                System.out.println("4.Transfer");
                System.out.println("5.Quit");
                int choice = input.nextInt();
                switch (choice) {
                    case 1 -> {
                        myAccount.transactionHistory();
                    }
                    case 2 -> {
                        myAccount.withdraw();
                    }
                    case 3 -> {
                        myAccount.deposit();
                    }
                    case 4 -> {
                        myAccount.transfer();
                    }
                    case 5 -> {
                        exit = false;
                    }
                    default -> {
                        exit = false;
                    }
                }
            }
        }

    }
}
