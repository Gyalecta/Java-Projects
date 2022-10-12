// Password generator using Java Swing that allows you to generate a random password by choosing how many characters
// Add comments for each step

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class main extends JFrame implements ActionListener {
    // Declaring the variables
    private JLabel label1, label2;
    private JTextField text1;
    private JButton button1;
    private JComboBox combo1;
    private String[] characters = {"8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
    private int amount, index1;
    private String password = "";

    public main() {
        // Setting the title
        super("Password Generator");
        // Setting the layout
        setLayout(new FlowLayout());
        // Creating the labels
        label1 = new JLabel("Amount");
        label2 = new JLabel("Password");
        // Creating the text fields
        text1 = new JTextField(10);
        // Creating the buttons
        button1 = new JButton("Generate");
        // Creating the combo boxes
        combo1 = new JComboBox(characters);
        // Adding the components to the frame
        add(label1);
        add(combo1);
        add(button1);
        add(label2);
        add(text1);
        // Adding the action listeners
        button1.addActionListener(this);
        combo1.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        // Getting the action command
        String action = e.getActionCommand();
        // Checking if the action command is equal to "Generate"
        if (action.equals("Generate")) {
            // Getting the selected item from the combo box
            amount = Integer.parseInt((String) combo1.getSelectedItem());
            // Generating the password
            for (int i = 0; i < amount; i++) {
                // Generating a random number between 33 and 126
                int random = new Random().nextInt(94) + 33;
                // Converting the random number to a character
                char character = (char) random;
                // Adding the character to the password
                password += character;
            }
            // Setting the text of the text field to the password
            text1.setText(password);
        }
    }

    public static void main(String[] args) {
        // Creating the frame
        main frame = new main();
        // Setting the size
        frame.setSize(300, 150);
        // Setting the location
        frame.setLocationRelativeTo(null);
        // Setting the default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Setting the visibility
        frame.setVisible(true);
    }
}