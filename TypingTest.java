import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TypingTest extends JFrame implements KeyListener {

    private JTextPane typingArea;
    private JLabel timerLabel;
    private String referenceText;
    private javax.swing.Timer countdownTimer;
    private int timeLeft;
    private int wordCount;
    private int correctChars, totalTypedChars;

    public TypingTest(String title, String referenceText) {
        super(title);
        this.referenceText = referenceText;
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JTextPane nonEditableArea = new JTextPane();
        typingArea = new JTextPane();

        JPanel typingPanel = new JPanel(new BorderLayout());
        typingPanel.add(new JScrollPane(nonEditableArea), BorderLayout.CENTER);

        nonEditableArea.setText(referenceText);
        nonEditableArea.setEditable(false);

        typingPanel.add(new JScrollPane(typingArea), BorderLayout.SOUTH);

        typingArea.addKeyListener(this);
        add(typingPanel, BorderLayout.CENTER);

        timerLabel = new JLabel("Time Left: 2:00");
        add(timerLabel, BorderLayout.NORTH);

        JButton endButton = new JButton("End Test");
        endButton.addActionListener(e -> endTypingTest());
        add(endButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        if (countdownTimer == null) {
            startCountdown(120); 
        }
        if (e.getKeyCode() == KeyEvent.VK_0) {
            System.exit(0); 
        }
    }

    public void keyReleased(KeyEvent e) {
        if (typingArea.isEditable()) {
            String typedText = typingArea.getText().trim();
            if (!typedText.isEmpty()) {
                highlightText(typedText);
                updateTypingStatistics(typedText);
                if (typedText.equals(referenceText)) {
                    endTypingTest(); 
                }
            }
        }
    }

    public void highlightText(String typedText) {
        try {
            StyledDocument doc = typingArea.getStyledDocument();
            removeHighlight();
            char[] referenceChars = referenceText.toCharArray();
            char[] typedChars = typedText.toCharArray();
            for (int i = 0; i < typedChars.length; i++) {
                Style style = doc.addStyle("Style", null);
                if (i < referenceChars.length && typedChars[i] == referenceChars[i]) {
                    StyleConstants.setForeground(style, Color.GREEN);
                } else {
                    StyleConstants.setForeground(style, Color.RED);
                }
                doc.setCharacterAttributes(i, 1, style, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeHighlight() {
        try {
            StyledDocument doc = typingArea.getStyledDocument();
            Style normalStyle = doc.addStyle("NormalStyle", null);
            StyleConstants.setForeground(normalStyle, Color.BLACK);
            doc.setCharacterAttributes(0, doc.getLength(), normalStyle, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startCountdown(int totalSeconds) {
        timeLeft = totalSeconds;
        countdownTimer = new javax.swing.Timer(1000, e -> {
            if (timeLeft > 0) {
                timeLeft--;
                int minutes = timeLeft / 60;
                int remainingSeconds = timeLeft % 60; 
                timerLabel.setText(String.format("Time Left: %d:%02d", minutes, remainingSeconds));
            } else {
                countdownTimer.stop();
                timerLabel.setText("Time's up!");
                showTypingStatistics();
                typingArea.setEditable(false);
            }
        });
        countdownTimer.start();
    }
    

    private void showTypingStatistics() {
        double wpm = (wordCount / 2.0);
        double accuracy = (double) correctChars / totalTypedChars * 100;
        JOptionPane.showMessageDialog(this, String.format("Your WPM: %.2f\nYour Accuracy: %.2f%%", wpm, accuracy));
    }

    private void updateTypingStatistics(String typedText) {
        String[] words = typedText.split("\\s+");
        wordCount = words.length;
        totalTypedChars = typedText.length();
        correctChars = 0;
        char[] referenceChars = referenceText.toCharArray();
        char[] typedChars = typedText.toCharArray();
        for (int i = 0; i < typedChars.length; i++) {
            if (i < referenceChars.length && typedChars[i] == referenceChars[i]) {
                correctChars++;
            }
        }
    }

    private void endTypingTest() {
        if (countdownTimer != null) {
            countdownTimer.stop();
        }
        showTypingStatistics();
        dispose(); 
    }
}
