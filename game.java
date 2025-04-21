import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class game extends JFrame implements ActionListener {
    private JTextField guessField;
    private JLabel messageLabel, attemptsLabel;
    private JButton guessButton, restartButton;
    private int randomNumber, attempts;

    public game() {
        // Set up frame
        setTitle("Number Guessing Game 🎯");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        // Initialize components
        messageLabel = new JLabel("Guess a number between 1 and 100", JLabel.CENTER);
        guessField = new JTextField();
        guessButton = new JButton("Guess");
        restartButton = new JButton("Restart");
        attemptsLabel = new JLabel("Attempts: 0", JLabel.CENTER);

        // Add action listeners
        guessButton.addActionListener(this);
        restartButton.addActionListener(this);

        // Add components to frame
        add(messageLabel);
        add(guessField);
        add(guessButton);
        add(restartButton);
        add(attemptsLabel);

        // Start the game
        resetGame();

        setVisible(true);
    }

    private void resetGame() {
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1; // Random number 1-100
        attempts = 0;
        messageLabel.setText("Guess a number between 1 and 100");
        attemptsLabel.setText("Attempts: 0");
        guessField.setText("");
        guessButton.setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guessButton) {
            try {
                int guess = Integer.parseInt(guessField.getText());
                attempts++;
                if (guess < randomNumber) {
                    messageLabel.setText("Too low! Try again.");
                } else if (guess > randomNumber) {
                    messageLabel.setText("Too high! Try again.");
                } else {
                    messageLabel.setText("🎉 Correct! You guessed in " + attempts + " attempts.");
                    guessButton.setEnabled(false); // Disable after correct guess
                }
                attemptsLabel.setText("Attempts: " + attempts);
            } catch (NumberFormatException ex) {
                messageLabel.setText("Enter a valid number!");
            }
        } else if (e.getSource() == restartButton) {
            resetGame(); // Restart game when button is clicked
        }
    }

    public static void main(String[] args) {
        new game();
    }
}
