package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;
import ca.ubc.cs304.model.RecipeUser;
import ca.ubc.cs304.model.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class UpdateUser extends JFrame {
    private DefaultTableModel UserTable;
    private TerminalTransactionsDelegate delegate;
    JTextField username = new JTextField(20);
    JTextField password = new JTextField(20);
    JTextField preferences = new JTextField(20);
    JTextField email = new JTextField(20);

    public UpdateUser(TerminalTransactionsDelegate delegate) {
        super("Update User");
        this.delegate = delegate;
        JPanel panel = new JPanel(new BorderLayout(20, 20));
        getContentPane().add(panel);
        setSize(400, 300);
        setLocationRelativeTo(null);

        UserTable = new DefaultTableModel();
        UserTable.addColumn("UserID");
        UserTable.addColumn("Username");
        UserTable.addColumn("Password");
        UserTable.addColumn("Preferences");
        UserTable.addColumn("Email");


        JComponent[] inputs = new JComponent[] {
                new JLabel("Username:"),
                username,
                new JLabel("Password:"),
                password,
                new JLabel("Preferences"),
                preferences,
                new JLabel("email"),
                email
        };


        User[] users = this.delegate.getUserInfo();
        for (User user : users) {
            Object[] rowData = {user.getUserID(), user.getUsername(), user.getPassword(),
                    user.getPreferences(), user.getEmail()};
            UserTable.addRow(rowData);
        }

        JTable uTable = new JTable(UserTable);
        JPanel tablesPanel = new JPanel(new GridLayout(1, 1, 20, 20)); // 1 row, 2 columns
        JScrollPane scrollPaneUser = new JScrollPane(uTable);
        tablesPanel.add(scrollPaneUser);
        panel.add(tablesPanel, BorderLayout.CENTER);
        JButton BackToMenu = new JButton("Back to Menu");

        uTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = uTable.getSelectedRow();
                    if (selectedRow != -1) {

                        int option = JOptionPane.showConfirmDialog(null, inputs, "Custom Input Dialog",
                                JOptionPane.PLAIN_MESSAGE, JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION) {
                            int userID = (int) uTable.getValueAt(selectedRow, 0);
                            try {
                                delegate.updateUser(userID, username.getText(), password.getText(), preferences.getText(), email.getText());
                                UserTable.setValueAt(username.getText(), selectedRow, 1);
                                UserTable.setValueAt(password.getText(), selectedRow, 2);
                                UserTable.setValueAt(preferences.getText(), selectedRow, 3);
                                UserTable.setValueAt(email.getText(), selectedRow, 4);

                                showSuccuess("User ID: " + userID + " Updated Successfully");
                            } catch (SQLException ex) {
                                showError("Failed to update user: " + ex.getMessage());
                            }
                        }
                    }
                }
            }
        });

        BackToMenu.setSize(10, 10);
        panel.add(BackToMenu, BorderLayout.EAST);

        BackToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MenuPage menuPage = new MenuPage(delegate);
                menuPage.setSize(400, 300);
                menuPage.setVisible(true);
            }
        });


    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showSuccuess(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.PLAIN_MESSAGE);
    }
}
