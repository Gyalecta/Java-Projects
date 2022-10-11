// Making a grading system using java swing
// The teacher can add students and their marks
// The teacher can also delete students and their marks
// The teacher can also edit students and their marks

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class main extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane pane;
    private JPanel panel;
    private JButton add, edit, delete;
    private JLabel name, mark;
    private JTextField tname, tmark;
    private String[] columns = {"Name", "Mark"};

    public main() {
        super("Grading System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        panel = new JPanel();
        panel.setLayout(null);

        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table = new JTable();
        table.setModel(model);
        table.setFont(new Font("Tahoma", Font.PLAIN, 15));
        table.setRowHeight(table.getRowHeight() + 10);
        pane = new JScrollPane(table);
        pane.setBounds(0, 0, 500, 200);
        panel.add(pane);

        name = new JLabel("Name:");
        name.setFont(new Font("Tahoma", Font.PLAIN, 20));
        name.setSize(100, 20);
        name.setLocation(100, 240);
        panel.add(name);

        tname = new JTextField();
        tname.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tname.setSize(190, 20);
        tname.setLocation(200, 240);
        panel.add(tname);

        mark = new JLabel("Mark:");
        mark.setFont(new Font("Tahoma", Font.PLAIN, 20));
        mark.setSize(100, 20);
        mark.setLocation(100, 280);
        panel.add(mark);

        tmark = new JTextField();
        tmark.setFont(new Font("Tahoma", Font.PLAIN, 15));
        tmark.setSize(150, 20);
        tmark.setLocation(200, 280);
        panel.add(tmark);

        add = new JButton("Add");
        add.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add.setSize(100, 20);
        add.setLocation(100, 320);
        add.addActionListener(this);
        panel.add(add);

        edit = new JButton("Edit");
        edit.setFont(new Font("Tahoma", Font.PLAIN, 15));
        edit.setSize(100, 20);
        edit.setLocation(200, 320);
        edit.addActionListener(this);
        panel.add(edit);

        delete = new JButton("Delete");
        delete.setFont(new Font("Tahoma", Font.PLAIN, 15));
        delete.setSize(100, 20);
        delete.setLocation(300, 320);
        delete.addActionListener(this);
        panel.add(delete);

        this.add(panel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            String n = tname.getText();
            String m = tmark.getText();
            if (n.equals("") || m.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter a name and a mark");
            } else {
                model.addRow(new Object[]{n, m});
                tname.setText("");
                tmark.setText("");
            }
        }
        if (e.getSource() == edit) {
            int i = table.getSelectedRow();
            if (i >= 0) {
                model.setValueAt(tname.getText(), i, 0);
                model.setValueAt(tmark.getText(), i, 1);
                tname.setText("");
                tmark.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to edit");
            }
        }
        if (e.getSource() == delete) {
            int i = table.getSelectedRow();
            if (i >= 0) {
                model.removeRow(i);
                tname.setText("");
                tmark.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to delete");
            }
        }
    }

    public static void main(String[] args) {
        new main();
    }
}

