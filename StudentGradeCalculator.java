import javax.swing.*;
import java.awt.event.*;
public class StudentGradeCalculator extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4, l5;
    JLabel r1, r2, r3, r4, r5, r6, r7, r8, r9;
    JTextField sub1, sub2, sub3, sub4, sub5;
    JButton b1, b2;
    public StudentGradeCalculator() {
        setTitle("STUDENT GRADE CALCULATOR");
        setSize(600, 650);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        l1 = new JLabel("JAVA");
        l1.setBounds(100, 50, 100, 30);
        add(l1);
        sub1 = new JTextField();
        sub1.setBounds(250, 50, 120, 30);
        add(sub1);
        l2 = new JLabel("DSA");
        l2.setBounds(100, 100, 100, 30);
        add(l2);
        sub2 = new JTextField();
        sub2.setBounds(250, 100, 120, 30);
        add(sub2);
        l3 = new JLabel("DBMS");
        l3.setBounds(100, 150, 100, 30);
        add(l3);
        sub3 = new JTextField();
        sub3.setBounds(250, 150, 120, 30);
        add(sub3);
        l4 = new JLabel("OS");
        l4.setBounds(100, 200, 100, 30);
        add(l4);
        sub4 = new JTextField();
        sub4.setBounds(250, 200, 120, 30);
        add(sub4);
        l5 = new JLabel("CN");
        l5.setBounds(100, 250, 100, 30);
        add(l5);
        sub5 = new JTextField();
        sub5.setBounds(250, 250, 120, 30);
        add(sub5);
        b1 = new JButton("CALCULATE");
        b1.setBounds(100, 320, 150, 40);
        b1.addActionListener(this);
        add(b1);
        b2 = new JButton("RESET");
        b2.setBounds(280, 320, 150, 40);
        b2.addActionListener(this);
        add(b2);
        r1 = new JLabel("");
        r1.setBounds(100, 390, 400, 30);
        add(r1);
        r2 = new JLabel("");
        r2.setBounds(100, 420, 400, 30);
        add(r2);
        r3 = new JLabel("");
        r3.setBounds(100, 450, 400, 30);
        add(r3);
        r4 = new JLabel("");
        r4.setBounds(100, 480, 400, 30);
        add(r4);
        r5 = new JLabel("");
        r5.setBounds(100, 510, 400, 30);
        add(r5);
        r6 = new JLabel("");
        r6.setBounds(100, 540, 400, 30);
        add(r6);
        r7 = new JLabel("");
        r7.setBounds(100, 570, 400, 30);
        add(r7);
        r8 = new JLabel("");
        r8.setBounds(100, 600, 400, 30);
        add(r8);
        r9 = new JLabel("");
        r9.setBounds(100, 630, 400, 30);
        add(r9);
        setVisible(true);
    }
    public String findGrade(int m) {
        if (m >= 90)
        {
            return "O";
        }
        else if (m >= 80)
        {
            return "A+";
        }
        else if (m >= 70)
        {
            return "A";
        }
        else if (m >= 60)
        {
            return "B+";
        }
        else if (m >= 50)
        {
            return "B";
        }
        else if (m >= 40)
        {
            return "C";
        }
        else if (m >= 35)
        {
            return "PASS";
        }
        else
        {
            return "FAIL";
        }
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1)
        {
            try
            {
                if (sub1.getText().equals("") ||
                    sub2.getText().equals("") ||
                    sub3.getText().equals("") ||
                    sub4.getText().equals("") ||
                    sub5.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(this,"All fields are required");
                    return;
                }
                int m1 = Integer.parseInt(sub1.getText());
                int m2 = Integer.parseInt(sub2.getText());
                int m3 = Integer.parseInt(sub3.getText());
                int m4 = Integer.parseInt(sub4.getText());
                int m5 = Integer.parseInt(sub5.getText());
                if (m1 < 0 || m1 > 100 ||
                    m2 < 0 || m2 > 100 ||
                    m3 < 0 || m3 > 100 ||
                    m4 < 0 || m4 > 100 ||
                    m5 < 0 || m5 > 100)
                {
                    JOptionPane.showMessageDialog(this,"Marks should be between 0 and 100");
                    return;
                }
                String g1 = findGrade(m1);
                String g2 = findGrade(m2);
                String g3 = findGrade(m3);
                String g4 = findGrade(m4);
                String g5 = findGrade(m5);
                int total = m1 + m2 + m3 + m4 + m5;
                double avg = total / 5.0;
                String overall;
                if (m1 < 35 || m2 < 35 || m3 < 35 || m4 < 35 || m5 < 35)
                {
                    overall = "FAIL";
                }
                else
                {
                    overall = findGrade((int)avg);
                }
                r1.setText("JAVA Grade : " + g1);
                r2.setText("DSA Grade : " + g2);
                r3.setText("DBMS Grade : " + g3);
                r4.setText("OS Grade : " + g4);
                r5.setText("CN Grade : " + g5);
                r6.setText("Total Marks : " + total);
                r7.setText("Average Percentage : " + avg + "%");
                r8.setText("Overall Grade : " + overall);
                if (overall.equals("FAIL"))
                {
                    r9.setText("Result : FAIL");
                }
                else
                {
                    r9.setText("Result : PASS");
                }
            }
            catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(this,"Please enter numbers only");
            }
            catch(NullPointerException ex)
            {
                JOptionPane.showMessageDialog(this,"Null value found");
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(this,"Invalid Input");
            }
        }
        if (e.getSource() == b2)
        {
            sub1.setText("");
            sub2.setText("");
            sub3.setText("");
            sub4.setText("");
            sub5.setText("");
            r1.setText("");
            r2.setText("");
            r3.setText("");
            r4.setText("");
            r5.setText("");
            r6.setText("");
            r7.setText("");
            r8.setText("");
            r9.setText("");
        }
    }
    public static void main(String[] args) {
        new StudentGradeCalculator();
    }
}