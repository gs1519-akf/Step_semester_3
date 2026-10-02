import java.util.Random;

public class RockPaperScissorsGame {

    private static final String[] MOVES = {"Rock", "Paper", "Scissors"};

    /**
     * Determines round winner between player and computer.
     * 
     * @param playerMove Player's chosen move.
     * @param computerMove Computer's chosen move.
     * @return "Player Wins", "Computer Wins", or "Draw".
     */
    public static String playRound(String playerMove, String computerMove) {
        if (playerMove.equalsIgnoreCase(computerMove)) {
            return "Draw";
        }
        if ((playerMove.equalsIgnoreCase("Rock") && computerMove.equalsIgnoreCase("Scissors")) ||
            (playerMove.equalsIgnoreCase("Paper") && computerMove.equalsIgnoreCase("Rock")) ||
            (playerMove.equalsIgnoreCase("Scissors") && computerMove.equalsIgnoreCase("Paper"))) {
            return "Player Wins";
        } else {
            return "Computer Wins";
        }
    }

    public static void main(String[] args) {
        // Predefined live demo moves matching sample demonstration
        String[] playerMoves = {"Rock", "Paper", "Scissors", "Rock", "Paper"};
        String[] computerMoves = {"Scissors", "Paper", "Rock", "Scissors", "Scissors"};

        int rounds = playerMoves.length;
        int wins = 0;
        int losses = 0;
        int draws = 0;

        String[][] summary = new String[rounds][4];

        for (int i = 0; i < rounds; i++) {
            String pMove = playerMoves[i];
            String cMove = computerMoves[i];
            String result = playRound(pMove, cMove);

            summary[i][0] = "Round " + (i + 1);
            summary[i][1] = pMove;
            summary[i][2] = cMove;
            summary[i][3] = result;

            if (result.equals("Player Wins")) {
                wins++;
            } else if (result.equals("Computer Wins")) {
                losses++;
            } else {
                draws++;
            }
            System.out.printf("Round %d — Player: %s, Computer: %s -> %s%n", (i + 1), pMove, cMove, result);
        }

        double winPercentage = ((double) wins / rounds) * 100.0;
        System.out.println("\nFinal Summary (after " + rounds + " rounds)");
        System.out.printf("Wins: %d | Losses: %d | Draws: %d | Win %% = %.1f%%%n", wins, losses, draws, winPercentage);
    }
}
