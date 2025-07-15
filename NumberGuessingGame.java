import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int rounds = 1;
        int score = 0;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("You need to guess a number between 1 and 100.");
        System.out.println("You have 5 attempts per round.\n");

        while (true) {
            int numberToGuess = rand.nextInt(100) + 1;
            int attempts = 5;
            boolean isGuessed = false;

            System.out.println("Round " + rounds + " begins!");

            for (int i = 1; i <= attempts; i++) {
                System.out.print("Attempt " + i + " Enter your guess: ");
                int userGuess = sc.nextInt();

                if (userGuess == numberToGuess) {
                    System.out.println("Correct! You guessed the number.");
                    score += (6 - i) * 10; // more score for fewer attempts
                    isGuessed = true;
                    break;
                } else if (userGuess < numberToGuess) {
                    System.out.println("Too low..!");
                } else {
                    System.out.println("Too high..!");
                }
            }

            if (!isGuessed) {
                System.out.println("Out of attempts! The correct number was: " + numberToGuess);
            }

            System.out.println("Your current score: " + score);

            System.out.print("Do you want to play another round? (yes/no): ");
            String choice = sc.next().toLowerCase();

            if (!choice.equals("yes")) {
                System.out.println("\nThanks for playing!");
                System.out.println("Final Score: " + score);
                break;
            }

            rounds++;
        }

        sc.close();
    }
}