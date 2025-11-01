/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author User
 */
public class LottoTech {
    public void play() {
        // Welcome message
        JOptionPane.showMessageDialog(null, "Welcome to Lottotech Mauritius! \nLottotech wishes you Good Luck!");
        
        // Ask if user wants to play
        int play = Integer.parseInt(JOptionPane.showInputDialog("Do you want to play?\nEnter 1 to play or -1 to exit."));
        if (play == -1) {
            JOptionPane.showMessageDialog(null, "Good Bye! \nSee you next time.");
            return;
        }

        // Get player's numbers
        int[] playerNumbers = getPlayerNumbers(); 
        
        // Generate winning numbers
        int[] winningNumbers = generateWinningNumbers();

        // Display player's numbers
        displayNumbers("Your Numbers:", playerNumbers);

        // Display winning numbers
        displayNumbers("Winning Numbers:", winningNumbers);

        // Check for matches and display result
        int matches = countMatches(playerNumbers, winningNumbers);
        displayResult(matches);
    } 

    public int[] getPlayerNumbers() {
        int[] numbers = new int[6];
        for (int i = 0; i < 6; i++) {
            int num;
            while (true) {
                String input = JOptionPane.showInputDialog("Enter number " + (i + 1) + " in the range of (1-40):");
                try {
                    num = Integer.parseInt(input);
                    if (num < 1 || num > 40) {
                        JOptionPane.showMessageDialog(null, "Number must be between 1 and 40.");
                    } else if (contains(numbers, num)) {
                        JOptionPane.showMessageDialog(null, "Duplicate number. Please try again.");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.");
                }
            }
            numbers[i] = num;
        }
        return numbers;
    }

    public int[] generateWinningNumbers() {
        int[] numbers = new int[6];
        for (int i = 0; i < 6; i++) {
            int num;
            while (true) {
                num = (int) (Math.random() * 40 + 1);
                if (!contains(numbers, num)) {
                    break;
                }
            }
            numbers[i] = num;
        }
        return numbers;
    }

    public void displayNumbers(String title, int[] numbers) {
        JTextArea textArea = new JTextArea(7, 20);
        textArea.setEditable(false);
        textArea.append(title + "\n");
        textArea.append("Array Cell\tNumber\n");
        for (int i = 0; i < 6; i++) {
            textArea.append(i + "\t" + numbers[i] + "\n");
        }
        JOptionPane.showMessageDialog(null, new JScrollPane(textArea), title, JOptionPane.INFORMATION_MESSAGE);
    }

    public int countMatches(int[] playerNumbers, int[] winningNumbers) {
        int matches = 0;
        for (int playerNum : playerNumbers) {
            for (int winningNum : winningNumbers) {
                if (playerNum == winningNum) {
                    matches++;
                    break;
                }
            }
        }
        return matches;
    }

    public void displayResult(int matches) {
        String message;
        switch (matches) {
            case 1:
                message = "You won Rs 100!";
                break;
            case 2:
                message = "You won Rs 500!";
                break;
            case 3:
                message = "You won Rs 1000!";
                break;
            case 4:
                message = "You won the jackpot of Rs 1,000,000!";
                break;
            default:
                message = "Better luck next time!";
        }
        JOptionPane.showMessageDialog(null, message);
    }

    public boolean contains(int[] array, int num) {
        for (int i : array) {
            if (i == num) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        LottoTech game = new LottoTech();
        game.play();
    }
}
