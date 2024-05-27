import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class TypingPractice extends JFrame {
    private List<String> sentences;
    private String currentSentence;
    private JLabel sentenceLabel;
    private JTextField inputField;
    private JTextPane outputPane;
    private long startTime;
    private boolean hasStartedTyping;

    public TypingPractice() {
        loadSentences("sentences.txt");
        setupUI();
    }

    private void loadSentences(String filename) {
        try {
            sentences = Files.lines(Paths.get(filename))
                             .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Błąd wczytywania pliku.", "Błąd", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    private void setupUI() {
        setTitle("Ćwiczenie pisania na klawiaturze");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        sentenceLabel = new JLabel("Kliknij 'Start' aby rozpocząć", SwingConstants.CENTER);
        sentenceLabel.setFont(new Font("Serif", Font.BOLD, 16));
        add(sentenceLabel, BorderLayout.NORTH);

        inputField = new JTextField();
        inputField.setFont(new Font("Serif", Font.PLAIN, 16));
        inputField.setEnabled(false);
        add(inputField, BorderLayout.CENTER);

        outputPane = new JTextPane();
        outputPane.setFont(new Font("Serif", Font.PLAIN, 16));
        outputPane.setEditable(false);
        add(new JScrollPane(outputPane), BorderLayout.SOUTH);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> startNewTry());
        add(startButton, BorderLayout.EAST);

        inputField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (!hasStartedTyping) {
                    hasStartedTyping = true;
                    sentenceLabel.setText("");
                    startTime = System.currentTimeMillis();
                }
            }

            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    confirmText();
                }
            }
        });

        inputField.setPreferredSize(new Dimension(800, 200));

        setVisible(true);
    }



    private void startNewTry() {
        Random random = new Random();
        currentSentence = sentences.get(random.nextInt(sentences.size()));
        sentenceLabel.setText("<html><span style='color:blue'>" + currentSentence + "</span></html>");
        inputField.setText("");
        outputPane.setText("");
        inputField.setEnabled(true);
        inputField.requestFocus();
        hasStartedTyping = false;
        startTime = 0;
    }

    private void confirmText() {
        long endTime = System.currentTimeMillis();
        String typedText = inputField.getText();

        int errors = checkErrors(currentSentence, typedText);
        double errorPercentage = (double) errors / currentSentence.length() * 100;
        errorPercentage = Math.max(0, 100 - errorPercentage); 
        showErrors(currentSentence, typedText);

        inputField.setEnabled(false);

        double timeTaken = (endTime - startTime) / 1000.0;

        String resultMessage = String.format("Poprawność: %.2f%%\nCzas: %.2f sekund", errorPercentage, timeTaken);

        JOptionPane.showMessageDialog(this, resultMessage, "Wynik", JOptionPane.INFORMATION_MESSAGE);

        int retry = JOptionPane.showConfirmDialog(this, "Czy chcesz spróbować ponownie?", "Ponowna próba", JOptionPane.YES_NO_OPTION);
        if (retry == JOptionPane.YES_OPTION) {
            startNewTry();
        }
    }

    private int checkErrors(String original, String typed) {
        int errors = 0;
        int minLength = Math.min(original.length(), typed.length());

        for (int i = 0; i < minLength; i++) {
            if (original.charAt(i) != typed.charAt(i)) {
                errors++;
            }
        }
        errors += Math.abs(original.length() - typed.length());
        return errors;
    }

    private void showErrors(String original, String typed) {
        StyledDocument doc = outputPane.getStyledDocument();
        Style defaultStyle = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
        Style errorStyle = doc.addStyle("ErrorStyle", defaultStyle);
        StyleConstants.setForeground(errorStyle, Color.RED);

        try {
            doc.remove(0, doc.getLength());

            doc.insertString(doc.getLength(), "Oryginalne zdanie:\n", defaultStyle);
            doc.insertString(doc.getLength(), original + "\n\n", defaultStyle);

            doc.insertString(doc.getLength(), "Twoje zdanie:\n", defaultStyle);

            int minLength = Math.min(original.length(), typed.length());
            for (int i = 0; i < minLength; i++) {
                if (original.charAt(i) == typed.charAt(i)) {
                    doc.insertString(doc.getLength(), String.valueOf(typed.charAt(i)), defaultStyle);
                } else {
                    doc.insertString(doc.getLength(), String.valueOf(typed.charAt(i)), errorStyle);
                }
            }

            if (typed.length() > original.length()) {
                doc.insertString(doc.getLength(), typed.substring(original.length()), errorStyle);
            } else if (original.length() > typed.length()) {
                StringBuilder remaining = new StringBuilder();
                for (int i = typed.length(); i < original.length(); i++) {
                    remaining.append("_");
                }
                doc.insertString(doc.getLength(), remaining.toString(), errorStyle);
            }

        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TypingPractice::new);
    }
}
