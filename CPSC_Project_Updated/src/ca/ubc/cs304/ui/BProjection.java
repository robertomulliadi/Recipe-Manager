package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;
import ca.ubc.cs304.model.RecipeUser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BProjection extends JFrame {

    static JFrame frame;
    static JComboBox<String> dropdown;
    static JPanel checkboxesPanel;

    private DefaultTableModel tableModel;

    private ArrayList<JCheckBox> checkboxes = new ArrayList<>();


    private TerminalTransactionsDelegate delegate;

    private String tableName;

    private ArrayList<String> selection;


    public BProjection(TerminalTransactionsDelegate delegate) {


        JFrame frame = new JFrame("Projection");

        this.delegate = delegate;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create dropdown menu
        JPanel panel = new JPanel();
        dropdown = new JComboBox<>(delegate.getTableNames());
        dropdown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkboxes = new ArrayList<>();
                tableName = (String) dropdown.getSelectedItem();
                displayAttributes(tableName);



            }
        });



        JButton project = new JButton("Project");

        project.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                boolean first = true;
                selection = new ArrayList<>();
                    for (JCheckBox checkbox : checkboxes) {
                        if (checkbox.isSelected()) {
//                        if (!first) {
//
//                        } else {
                            selection.add(checkbox.getText());
                            first = false;
//                        }
                        }
                    }

                    DisplayTable newTable = new DisplayTable(tableModel, delegate, selection, tableName);
                    panel.disable();
                    frame.dispose();

                } catch (Exception exception) {
                    showError("please select an attribute");
                }
            }
        });
        panel.add(dropdown);
        frame.add(panel, BorderLayout.NORTH);

        // Create panel for checkboxes
        checkboxesPanel = new JPanel();
        checkboxesPanel.setLayout(new GridLayout(0, 1));
        frame.add(checkboxesPanel, BorderLayout.CENTER);

        panel.add(project);

        JButton BackToMenu = new JButton("Back to Menu");
        panel.add(BackToMenu, BorderLayout.EAST);

        BackToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MenuPage menuPage = new MenuPage(delegate);
                menuPage.setSize(400,300);
                menuPage.setVisible(true);

            }
        });

        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    private void displayAttributes(String tableName) {
        // Clear existing checkboxes
        checkboxesPanel.removeAll();

        // Get sample attributes (replace with actual attributes)
        String[] colNames = delegate.getColNames(tableName);

        // Create checkboxes for attributes
        for (String attribute : colNames) {
            JCheckBox checkbox = new JCheckBox(attribute);
            checkboxesPanel.add(checkbox);
            checkboxes.add(checkbox);
        }

        // Repaint the checkboxes panel to reflect changes
        checkboxesPanel.revalidate();
        checkboxesPanel.repaint();
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
