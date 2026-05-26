import javax.swing.*;
import java.awt.event.*;
public class ATMInterface extends JFrame implements ActionListener {
    JLabel l1, l2;
    JTextField tf;
    JTextArea ta;
    JScrollPane sp;
    JButton b1, b2, b3, b4;
    BankAccount acc;
    public ATMInterface() {
        acc = new BankAccount();
        setTitle("ATM INTERFACE");
        setSize(550, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        l1 = new JLabel("Enter Amount");
        l1.setBounds(80, 40, 150, 30);
        add(l1);
        tf = new JTextField();
        tf.setBounds(220, 40, 150, 30);
        add(tf);
        b1 = new JButton("DEPOSIT");
        b1.setBounds(30, 110, 120, 40);
        b1.addActionListener(this);
        add(b1);
        b2 = new JButton("WITHDRAW");
        b2.setBounds(160, 110, 120, 40);
        b2.addActionListener(this);
        add(b2);
        b3 = new JButton("CHECK BALANCE");
        b3.setBounds(290, 110, 160, 40);
        b3.addActionListener(this);
        add(b3);
        b4 = new JButton("EXIT");
        b4.setBounds(180, 180, 120, 40);
        b4.addActionListener(this);
        add(b4);
        l2 = new JLabel("Current Balance : Rs. "+ acc.checkBalance());
        l2.setBounds(140, 250, 250, 30);
        add(l2);
        ta = new JTextArea();
        ta.setEditable(false);
        sp = new JScrollPane(ta);
        sp.setBounds(70, 300, 380, 120);
        add(sp);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1)
        {
            try
            {
                if (tf.getText().equals(""))
                {

                    JOptionPane.showMessageDialog(this,"Please enter amount");
                    return;
                }
                double amt = Double.parseDouble(tf.getText());
                if (amt <= 0)
                {
                    JOptionPane.showMessageDialog(this,"Amount should be greater than 0");
                    tf.setText("");
                    return;
                }
                if (amt > 100000)
                {
                    JOptionPane.showMessageDialog(this,"Amount limit exceeded");
                    tf.setText("");
                    return;
                }
                acc.deposit(amt);
                l2.setText("Current Balance : Rs. "+ acc.checkBalance());
                ta.setText("Amount Deposited : Rs. "+ amt +"\nUpdated Balance : Rs. "+ acc.checkBalance());
                tf.setText("");
            }
            catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(this,"Please enter numbers only");
                tf.setText("");
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
            try
            {
                if (tf.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(this,"Please enter amount");
                    return;
                }
                double amt = Double.parseDouble(tf.getText());
                if (amt <= 0)
                {
                    JOptionPane.showMessageDialog(this,"Amount should be greater than 0");
                    tf.setText("");
                    return;
                }
                if (amt > 100000)
                {
                    JOptionPane.showMessageDialog(this,"Amount limit exceeded");
                    tf.setText("");
                    return;
                }
                boolean x = acc.withdraw(amt);
                if (!x)
                {
                    JOptionPane.showMessageDialog(this,
                            "Insufficient Balance");
                    tf.setText("");
                    return;
                }
                l2.setText("Current Balance : Rs. "+ acc.checkBalance());
                ta.setText("Amount Withdrawn : Rs. "+ amt +"\nUpdated Balance : Rs. "+ acc.checkBalance());
                tf.setText("");
            }
            catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(this,"Please enter numbers only");
                tf.setText("");
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
        if (e.getSource() == b3)
        {
            ta.setText("Available Balance : Rs. "+ acc.checkBalance());
        }
        if (e.getSource() == b4)
        {
            int x = JOptionPane.showConfirmDialog(this,"Do you want to exit ?");
            if (x == JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
        }
    }
    public static void main(String[] args) {
        new ATMInterface();
    }
}