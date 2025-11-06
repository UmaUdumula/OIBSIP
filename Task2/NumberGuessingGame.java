import java.util.*;
public class NumberGuessingGame{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        String playAgain = "yes";
        System.out.println("Welcome to the Number Guessing Game");
        while (playAgain.equalsIgnoreCase("yes")) {
            int numberToGuess = rand.nextInt(100) + 1;
            int attempts = 0;
            int maxAttempts = 7;
            int score = 100 ;
            boolean guessed = false;
            System.out.println("\nI have chosen a number between 1 and 100.");
            System.out.println("You have " + maxAttempts + "attempts to guess it.");
            while (attempts < maxAttempts){
               System.out.print("Enter your guess:");
                int guess = sc.nextInt();
                attempts++;
                if(guess == numberToGuess){
                    System.out.println("Correct! You gussed it in " + attempts + "attempts.");
                    guessed = true;
                    break;
                }
                else if (guess < numberToGuess){
                    System.out.println("Too Low! Try again. ");
                    score = score -10;
                }
                else {
                    System.out.println("To High! Try again.");
                    score = score - 10;
                }
         }
         if(!guessed){
            System.out.println("Out of attempts! The number was "+ numberToGuess);
            score = 0;
         }

         System.out.println("Your score this round: " + score);
         System.out.println("\nDo you want to play again? (yes/no): ");
         playAgain = sc.next();
        }
        System.out.println("\nGame Over! Thanks for playing");
        sc.close();
    }
}
