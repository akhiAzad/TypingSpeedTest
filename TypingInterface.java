import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TypingInterface extends JFrame implements ActionListener {

    private JButton banglaButton, englishButton;

    public TypingInterface() {
        setTitle("SpeedTest");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel instructionPanel = new JPanel(new GridLayout(1, 2));

        JLabel banglaInstructionLabel = new JLabel("<html><h3 style='text-align:center;'>Bangla Typing Instructions:</h3>"
                + "<ul style='text-align:left;'>"
                + "<li>Ensure your keyboard is set to Bangla layout.</li>"
                + "<li>Use Bangla characters as per your keyboard mapping.</li>"
                + "<li>If using an IME, activate it before typing.</li>"
                + "</ul></html>");
        banglaInstructionLabel.setVerticalAlignment(SwingConstants.TOP);

        JLabel englishInstructionLabel = new JLabel("<html><h3 style='text-align:center;'>English Typing Instructions:</h3>"
                + "<ul style='text-align:left;'>"
                + "<li>Use the standard QWERTY layout for typing.</li>"
                + "<li>Type directly in the text area for English.</li>"
                + "<li>Use punctuation keys for correct writing.</li>"
                + "</ul></html>");
        englishInstructionLabel.setVerticalAlignment(SwingConstants.TOP);

        instructionPanel.add(banglaInstructionLabel);
        instructionPanel.add(englishInstructionLabel);

        add(instructionPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        banglaButton = new JButton("Bangla Typing");
        englishButton = new JButton("English Typing");
        banglaButton.addActionListener(this);
        englishButton.addActionListener(this);
        buttonPanel.add(banglaButton);
        buttonPanel.add(englishButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == banglaButton) {
            new TypingTest("Bangla Typing Mode", PassageProvider.getBanglaPassage());
        } else if (e.getSource() == englishButton) {
            new TypingTest("English Typing Mode", PassageProvider.getEnglishPassage());
        }
        dispose();
    }
}
