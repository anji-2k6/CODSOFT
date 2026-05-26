import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
public class CurrencyConverter extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4;
    JTextField tf1;
    JComboBox<String> cb1, cb2;
    JButton b1, b2;
    JTextArea ta;
    JScrollPane sp;
    String arr[] = {"USD", "INR", "EUR", "GBP", "JPY", "AUD", "CAD"};
    public CurrencyConverter() {
        setTitle("CURRENCY CONVERTER");
        setSize(600, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        l1 = new JLabel("Enter Amount");
        l1.setBounds(70, 40, 120, 30);
        add(l1);
        tf1 = new JTextField();
        tf1.setBounds(220, 40, 180, 30);
        add(tf1);
        l2 = new JLabel("From Currency");
        l2.setBounds(70, 100, 120, 30);
        add(l2);
        cb1 = new JComboBox<String>(arr);
        cb1.setBounds(220, 100, 180, 30);
        add(cb1);
        l3 = new JLabel("To Currency");
        l3.setBounds(70, 160, 120, 30);
        add(l3);
        cb2 = new JComboBox<String>(arr);
        cb2.setBounds(220, 160, 180, 30);
        add(cb2);
        b1 = new JButton("CONVERT");
        b1.setBounds(120, 230, 140, 40);
        b1.addActionListener(this);
        add(b1);
        b2 = new JButton("CLEAR");
        b2.setBounds(300, 230, 140, 40);
        b2.addActionListener(this);
        add(b2);
        l4 = new JLabel("Conversion Result");
        l4.setBounds(200, 290, 200, 30);
        add(l4);
        ta = new JTextArea();
        ta.setEditable(false);
        sp = new JScrollPane(ta);
        sp.setBounds(80, 330, 420, 100);
        add(sp);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1)
        {
            try
            {
                if (tf1.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(this,"Please enter amount");
                    return;
                }
                double amt = Double.parseDouble(tf1.getText());
                if (amt <= 0)
                {
                    JOptionPane.showMessageDialog(this,"Amount should be greater than 0");
                    tf1.setText("");
                    return;
                }
                if (amt > 1000000)
                {
                    JOptionPane.showMessageDialog(this,"Amount too large");
                    tf1.setText("");
                    return;
                }
                String from = cb1.getSelectedItem().toString();
                String to = cb2.getSelectedItem().toString();
                if (from.equals(to))
                {
                    JOptionPane.showMessageDialog(this,"Both currencies cannot be same");
                    return;
                }
                String link ="https://api.frankfurter.app/latest?from="+ from +"&to=" +to;
                URL url = new URL(link);
                HttpURLConnection con =(HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                int code = con.getResponseCode();
                if (code != 200)
                {
                    JOptionPane.showMessageDialog(this,"API Connection Failed");
                    return;
                }
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String input;
                String data = "";
                while ((input = br.readLine()) != null)
                {
                    data = data + input;
                }
                br.close();
                int i = data.indexOf(to);
                int j = data.indexOf(":", i);
                int k = data.indexOf("}", j);
                String rateStr =data.substring(j + 1, k);
                double rate =Double.parseDouble(rateStr);
                double result = amt * rate;
                ta.setText("From Currency : " + from +"\nTo Currency : " + to + "\nExchange Rate : " + rate + "\nEntered Amount : " + amt + "\nConverted Amount : " + result);
            }
            catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(this,"Please enter valid numbers only");
                tf1.setText("");
            }
            catch(MalformedURLException ex)
            {
                JOptionPane.showMessageDialog(this,"Invalid URL");
            }
            catch(UnknownHostException ex)
            {
                JOptionPane.showMessageDialog(this,"No Internet Connection");
            }
            catch(IOException ex)
            {
                JOptionPane.showMessageDialog(this,"Error while connecting to API");
            }
            catch(NullPointerException ex)
            {
                JOptionPane.showMessageDialog(this,"Null value found");
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(this,"Unexpected Error");
            }
        }
        if (e.getSource() == b2)
        {
            tf1.setText("");
            ta.setText("");
            cb1.setSelectedIndex(0);
            cb2.setSelectedIndex(1);
        }
    }
    public static void main(String[] args) {
       new CurrencyConverter();
    }
}