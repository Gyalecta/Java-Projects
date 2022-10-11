// Making a GUI Currency Converter Software
// The software should let the user insert the amount and the current currency
// And should also be able to select the currency for the conversion

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.DecimalFormat;

public class main extends JFrame implements ActionListener {
    // Declaring the variables
    private JLabel label1, label2, label3, label4;
    private JTextField text1, text2;
    private JButton button1, button2;
    private JComboBox combo1, combo2;
    private String[] currency = {"USD", "EUR", "GBP", "JPY", "AUD", "CAD", "CHF", "CNY", "SEK", "NZD", "MXN", "SGD", "HKD", "NOK", "KRW", "TRY", "RUB", "INR", "BRL", "ZAR"};
    private double[] rate = {1.0, 0.89, 0.78, 106.88, 1.43, 1.32, 0.99, 6.92, 9.54, 1.51, 19.74, 1.37, 7.75, 8.67, 1138.20, 3.75, 63.16, 68.50, 3.31, 14.47};
    private double amount, result;
    private int index1, index2;
    private DecimalFormat df = new DecimalFormat("#.##");

    public main() {
        // Setting the title
        super("Currency Converter");
        // Setting the layout
        setLayout(new FlowLayout());
        // Creating the labels
        label1 = new JLabel("Amount");
        label2 = new JLabel("From");
        label3 = new JLabel("To");
        label4 = new JLabel("Result");
        // Creating the text fields
        text1 = new JTextField(10);
        text2 = new JTextField(10);
        // Creating the buttons
        button1 = new JButton("Convert");
        button2 = new JButton("Clear");
        // Creating the combo boxes
        combo1 = new JComboBox(currency);
        combo2 = new JComboBox(currency);
        // Adding the components to the frame
        add(label1);
        add(text1);
        add(label2);
        add(combo1);
        add(label3);
        add(combo2);
        add(button1);
        add(button2);
        add(label4);
        add(text2);
        // Adding the action listeners
        button1.addActionListener(this);
        button2.addActionListener(this);
        combo1.addActionListener(this);
        combo2.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        // Getting the action command
        String action = e.getActionCommand();
        // Checking if the action command is equal to "Convert"
        if (action.equals("Convert")) {
            // Getting the amount
            amount = Double.parseDouble(text1.getText());
            // Getting the index of the selected currency
            index1 = combo1.getSelectedIndex();
            index2 = combo2.getSelectedIndex();
            // Calculating the result
            result = amount * rate[index2] / rate[index1];
            // Setting the result
            text2.setText(df.format(result));
        }
        // Checking if the action command is equal to "Clear"
        if (action.equals("Clear")) {
            // Clearing the text fields
            text1.setText("");
            text2.setText("");
        }
    }

    public static void main(String[] args) {
        // Creating the frame
        main frame = new main();
        // Setting the size
        frame.setSize(300, 200);
        // Setting the default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Setting the visibility
        frame.setVisible(true);
    }
}