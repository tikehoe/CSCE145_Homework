import java.util.Random;
import java.util.Scanner;

public class Homework03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        boolean playAgain;

        do {
            int playerScore = 0;
            int computerScore = 0;

            // Best 2 out of 3 rounds
            for (int round = 1; round <= 3; round++) {
                System.out.println("Round " + round + ": Enter rock, paper, or scissors:");
                String playerChoice = scanner.nextLine().toLowerCase();

                // Validate input
                if (!isValidChoice(playerChoice)) {
                    System.out.println("Invalid choice! Computer scores a point.");
                    computerScore++;
                    continue; // Skip to next round
                }

                // Computer makes a choice
                String computerChoice = getComputerChoice(random);
                System.out.println("Computer chose: " + computerChoice);

                // Determine round winner
                int result = determineRoundWinner(playerChoice, computerChoice);
                if (result == 1) {
                    System.out.println("You win this round!");
                    playerScore++;
                } else if (result == -1) {
                    System.out.println("Computer wins this round!");
                    computerScore++;
                } else {
                    System.out.println("It's a draw!");
                }

                // Display current scores
                System.out.println("Score - You: " + playerScore + " | Computer: " + computerScore);
            }

            // Declare overall winner
            declareOverallWinner(playerScore, computerScore);

            // Ask to play again
            System.out.println("Do you want to play again? (yes/no)");
            String response = scanner.nextLine().toLowerCase();
            playAgain = response.equals("yes");

        } while (playAgain);

        System.out.println("Thank you for playing!");
        scanner.close();
    }

    private static boolean isValidChoice(String choice) {
        return choice.equals("rock") || choice.equals("paper") || choice.equals("scissors");
    }

    private static String getComputerChoice(Random random) {
        int choice = random.nextInt(3); // Generates 0, 1, or 2
        return switch (choice) {
            case 0 -> "rock";
            case 1 -> "paper";
            case 2 -> "scissors";
            default -> ""; // This should never happen
        };
    }

    private static int determineRoundWinner(String player, String computer) {
        if (player.equals(computer)) {
            return 0; // Draw
        }
        return switch (player) {
            case "rock" -> (computer.equals("scissors") ? 1 : -1);
            case "paper" -> (computer.equals("rock") ? 1 : -1);
            case "scissors" -> (computer.equals("paper") ? 1 : -1);
            default -> 0; // Should not reach here due to input validation
        };
    }

    private static void declareOverallWinner(int playerScore, int computerScore) {
        if (playerScore > computerScore) {
            System.out.println("You are the overall winner!");
        } else if (computerScore > playerScore) {
            System.out.println("Computer is the overall winner!");
        } else {
            System.out.println("It's a tie!");
        }
    }
}

