import java.util.Random;
import javax.swing.*;
import java.awt.event.*;
public class NumberGame extends JFrame implements ActionListener {
    JTextField tf;
    JButton b1, b2;
    JLabel l1, l2, l3;
    Random random = new Random();
    int score = 0;
    int ranNum;
    int attempts = 0;
    int maxAttempts = 5;
    boolean flag = false;
    public NumberGame() {
        setTitle("NUMBER GUESSING GAME");
        setSize(400, 300);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        l1 = new JLabel("Guess the number between 1 and 100");
        l1.setBounds(70, 30, 250, 30);
        add(l1);
        l2 = new JLabel("You have " + maxAttempts + " attempts.");
        l2.setBounds(110, 60, 200, 30);
        add(l2);
        tf = new JTextField();
        tf.setBounds(120, 100, 150, 30);
        add(tf);
        b1 = new JButton("Guess");
        b1.setBounds(140, 140, 100, 30);
        b1.addActionListener(this);
        add(b1);
        l3 = new JLabel("");
        l3.setBounds(50, 180, 300, 30);
        add(l3);
        b2 = new JButton("Play Again");
        b2.setBounds(120, 220, 140, 30);
        b2.addActionListener(this);
        b2.setVisible(false);
        add(b2);
        ranNum = random.nextInt(100) + 1;
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1)
        {
            if (tf.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this,"Please enter a number");
                return;
            }
            int guess;
            try
            {
                guess = Integer.parseInt(tf.getText());
            }
            catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(this,"Please enter numbers only");
                tf.setText("");
                return;
            }
            if (guess < 1 || guess > 100)
            {
                JOptionPane.showMessageDialog(this,"Enter number between 1 and 100");
                tf.setText("");
                return;
            }
            attempts++;
            if (guess == ranNum)
            {
                l3.setText("Correct! You guessed the number.");
                score++;
                flag = true;
                b1.setEnabled(false);
                b2.setVisible(true);
                JOptionPane.showMessageDialog(this,"Attempts used: " + attempts +"\nCurrent Score: " + score);
            }
            else if (guess > ranNum)
            {
                l3.setText("Too high!");
            }
            else
            {
                l3.setText("Too low!");
            }
            if (!flag)
            {
                if (attempts < maxAttempts)
                {
                    l2.setText("Remaining attempts: "
                            + (maxAttempts - attempts));
                }
                else
                {
                    l3.setText("You lost! Number was: " + ranNum);
                    b1.setEnabled(false);
                    b2.setVisible(true);
                    JOptionPane.showMessageDialog(this, "Game Over!");
                }
            }
            tf.setText("");
        }
        if (e.getSource() == b2)
        {
            ranNum = random.nextInt(100) + 1;
            attempts = 0;
            flag = false;
            l2.setText("You have "+ maxAttempts + " attempts.");
            l3.setText("");
            tf.setText("");
            b1.setEnabled(true);
            b2.setVisible(false);
        }
    }
    public static void main(String[] args) {
        new NumberGame();
    }
}