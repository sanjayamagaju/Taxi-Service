import com.mysql.cj.protocol.Resultset;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//Creating Class which extends Jframe class

public class SampleApp extends JFrame {
    JPanel topPanel;
    JPanel dataPanel;
    JPanel secondPanel;
    UserRegistration reg;

    // Reference variable for different fields, buttons, table and table model

    DefaultTableModel model;
    JTable table;
    JTextField firstName, lastName, regNo;
    JButton btnSave, btnUpdate, btnDelete;

    public SampleApp() {
        setTitle("CRUD");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));
        JPanel subPanel = new JPanel(new BorderLayout());
        subPanel.add(topUI(),BorderLayout.NORTH);
        subPanel.add(dataUI(),BorderLayout.WEST);
        add(subPanel);
        subPanel.add(secondUI(),BorderLayout.EAST);
        reg = new UserRegistration();
        refreshTable();
        pack();
        setLocationRelativeTo(null);
    }

    //The method created below is for title section
    private JPanel topUI() {
        topPanel = new JPanel();
        topPanel.setLayout(new GridBagLayout());
        topPanel.setBorder(BorderFactory.createTitledBorder("SIMPLE CRUD APPLICATION"));


        return topPanel;
    }

    //The method created below is for datapanel.
    private JPanel dataUI() {

        dataPanel = new JPanel();
        dataPanel.setLayout(new GridBagLayout());
        dataPanel.setBorder(BorderFactory.createTitledBorder(""));
        btnSave = new JButton("Save");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        dataPanel.add(btnSave);
        dataPanel.add(btnUpdate);
        dataPanel.add(btnDelete);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(50, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        dataPanel.add(new JLabel("FirstName"), gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        dataPanel.add(new JLabel("LastName"), gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        dataPanel.add(new JLabel("Reg Number"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        firstName = new JTextField(20);
        dataPanel.add(firstName, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        lastName = new JTextField(20);
        dataPanel.add(lastName, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        regNo = new JTextField(20);
        dataPanel.add(regNo, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        dataPanel.add(btnSave, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        dataPanel.add(btnUpdate, gbc);
        gbc.gridx = 2;
        gbc.gridy = 3;
        dataPanel.add(btnDelete, gbc);





        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstname = firstName.getText().trim();
                String lastname = lastName.getText().trim();
                String regnumber = regNo.getText().trim();

                if (firstname.isEmpty() || lastname.isEmpty() || regnumber.isEmpty()) {
                    JOptionPane.showMessageDialog(dataPanel, "All fields are not filled some are empty.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {

                    reg.insert(firstname, lastname,regnumber);
                    refreshTable();
                    JOptionPane.showMessageDialog(dataPanel, "Data has been saved.", "Success", JOptionPane.INFORMATION_MESSAGE);

                }

            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                String firstname = firstName.getText().trim();
                String lastname = lastName.getText().trim();
                String regnumber = regNo.getText().trim();

                if (firstname.isEmpty() || lastname.isEmpty()) {
                    JOptionPane.showMessageDialog(dataPanel, "All fields are not filled some are empty", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String regNo = (model.getValueAt(selectedRow, 0).toString());
                    reg.update(firstname, lastname,regnumber);
                    refreshTable();

                    JOptionPane.showMessageDialog(dataPanel, "Data has been Updated.", "Success", JOptionPane.INFORMATION_MESSAGE);


                }
            }
        });


        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String row = JOptionPane.showInputDialog(dataPanel, "Please input row number(ID)to be deleted?", "Queries", JOptionPane.QUESTION_MESSAGE);
                int confirm = JOptionPane.showConfirmDialog(dataPanel, "Are you confirm to delete?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        int rowDelete = Integer.parseInt(row);
                     //   model.removeRow(rowDelete - 1);
                        reg.delete(rowDelete);
                        refreshTable();
                    } catch (NumberFormatException exception) {
                        JOptionPane.showMessageDialog(dataPanel, "Please input a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (ArrayIndexOutOfBoundsException exception) {
                        JOptionPane.showMessageDialog(dataPanel, "The row you input is unavilable.Please input a row number which is Valid.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });
        return dataPanel;
    }

    private JPanel secondUI() {
        secondPanel = new JPanel(new BorderLayout());
        secondPanel.setBorder(BorderFactory.createTitledBorder(""));
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{ "FirstName", "LastName", "Id Number",});
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        secondPanel.add(scrollPane);

        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int selectedRow = table.getSelectedRow();

                firstName.setText(model.getValueAt(selectedRow, 0).toString());
                lastName.setText(model.getValueAt(selectedRow, 1).toString());
                regNo.setText(model.getValueAt(selectedRow, 2).toString());

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        return secondPanel;
    }

    //Data list is removed from JTable:Refreshlist//
    private void refreshTable(){
        model.setRowCount(0);
        try {
            ResultSet resultSet = reg.get();
            while (resultSet.next()) {
                model.addRow(new Object[]{
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("reg_number"),
                });
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SampleApp();
    }
}
