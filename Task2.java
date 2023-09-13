import java.util.Random;
import java.util.Scanner;
public class GuessTheNumber {
     public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        boolean exit = true;
        int guess = 0;
        int randomNumber = random.nextInt(101);
        while (exit) {
            if (guess >= 10) {
                System.out.println("You have reached the limit of attempts");
                break; 
            } 
            System.out.println("Plese Enter the Guessed Number"); 
            int guessedNumber = input.nextInt(); guess++; 
            if (guessedNumber > randomNumber) { 
                System.out.println("You have Entered a large number, please decrease the number");
            } else if (guessedNumber < randomNumber) { 
                System.out.println("You have Entered a small number, please increase the number"); 
            } else { 
                System.out .println("/Congratlations! you have sucessfully guessed the number in" + guess+"attemptts");
                System.out.println("you have earned"+guess*10+"points");
                int start = input.nextInt();
                if (start == 1) {
                    randomNumber = random.nextInt(101);
                    guess = 0;

                } else {
                    exit = false;
                }
            }
        }
    }
}
