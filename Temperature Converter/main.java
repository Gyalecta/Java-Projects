// Temperature converter that allows to convert Kelvin, Fahrenheit, Celsius, etc...

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
    private String[] temperature = {"Celsius", "Fahrenheit", "Kelvin"};
    private double amount, result;
    private int index1, index2;
    private DecimalFormat df = new DecimalFormat("#.##");

    public main() {
        // Setting the title
        super("Temperature Converter");
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
        combo1 = new JComboBox(temperature);
        combo2 = new JComboBox(temperature);
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
            try {
                // Getting the amount from the text field
                amount = Double.parseDouble(text1.getText());
                // Getting the selected items
                index1 = combo1.getSelectedIndex();
                index2 = combo2.getSelectedIndex();
                // Checking if the same items are selected
                if (index1 == index2) {
                    text2.setText("0");
                } else {
                    // Converting the temperature
                    if (index1 == 0 && index2 == 1) {
                        result = amount * 9 / 5 + 32;
                    } else if (index1 == 0 && index2 == 2) {
                        result = amount + 273.15;
                    } else if (index1 == 1 && index2 == 0) {
                        result = (amount - 32) * 5 / 9;
                    } else if (index1 == 1 && index2 == 2) {
                        result = (amount - 32) * 5 / 9 + 273.15;
                    } else if (index1 == 2 && index2 == 0) {
                        result = amount - 273.15;
                    } else if (index1 == 2 && index2 == 1) {
                        result = (amount - 273.15) * 9 / 5 + 32;
                    }
                    // Setting the result
                    text2.setText(df.format(result));
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (action.equals("Clear")) {
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
        // Setting the location
        frame.setLocationRelativeTo(null);
        // Setting the default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Setting the visibility
        frame.setVisible(true);
    }
}