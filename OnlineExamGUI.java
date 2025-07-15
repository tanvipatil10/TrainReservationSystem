import javax.swing.*;
import java.awt.event.*;

public class OnlineExamGUI extends JFrame implements ActionListener {
    JLabel questionLabel, timerLabel;
    JRadioButton[] options = new JRadioButton[4];
    ButtonGroup bg;
    JButton nextButton;
    Timer timer;
    int timeLeft = 30;
    int current = 0, score = 0;

    // {question, option1, option2, option3, option4, correctOptionLetter}
    String[][] questions = {
        {"What is the full form of OOP?", "Object-Oriented Programming", "Ordinary Object Processing", "Outer Object Program", "None", "A"},
        {"Which of these is not a Java keyword?", "if", "void", "then", "static", "C"},
        {"Who developed Java?", "Google", "Microsoft", "Sun Microsystems", "Amazon", "C"},
        {"Java is __ language.", "Procedural", "Functional", "Object-Oriented", "All of the above", "D"},
        {"JVM stands for?", "Java Virtual Method", "Java Very Memory", "Java Visual Machine", "Java Virtual Machine", "D"}
    };

    public OnlineExamGUI() {
        setTitle("Online Examination System");
        setSize(500, 300);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        questionLabel = new JLabel();
        questionLabel.setBounds(50, 30, 400, 20);
        add(questionLabel);

        bg = new ButtonGroup();
        int y = 70;
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            options[i].setBounds(50, y, 400, 20);
            bg.add(options[i]);
            add(options[i]);
            y += 30;
        }

        nextButton = new JButton("Next");
        nextButton.setBounds(180, 200, 100, 30);
        nextButton.addActionListener(this);
        add(nextButton);

        timerLabel = new JLabel("Time Left: 30s");
        timerLabel.setBounds(370, 10, 120, 20);
        add(timerLabel);

        setQuestion();

        timer = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText("Time Left: " + timeLeft + "s");
            if (timeLeft <= 0) {
                timer.stop();
                showResult();
            }
        });
        timer.start();

        setVisible(true);
    }

    void setQuestion() {
        bg.clearSelection();
        questionLabel.setText((current + 1) + ". " + questions[current][0]);
        for (int i = 0; i < 4; i++) {
            options[i].setText(questions[current][i + 1]);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedIndex = -1;
        for (int i = 0; i < options.length; i++) {
            if (options[i].isSelected()) {
                selectedIndex = i;
                break;
            }
        }

        if (selectedIndex != -1) {
            char selectedOption = (char) ('A' + selectedIndex); // Convert index 0 → A, 1 → B, etc.
            if (selectedOption == questions[current][5].charAt(0)) {
                score++;
            }
        }

        current++;
        if (current < questions.length) {
            setQuestion();
        } else {
            timer.stop();
            showResult();
        }
    }

    void showResult() {
        JOptionPane.showMessageDialog(this,
                "✅ Exam Completed!\n📊 Your Score: " + score + " out of " + questions.length,
                "Result",
                JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(OnlineExamGUI::new);
    }
}