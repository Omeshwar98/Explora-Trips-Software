package explora.trips;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.border.*;
import net.proteanit.sql.DbUtils;

public class BookedPackagePanel extends JPanel {

    JPanel panel;
    JLabel l1;
    JTable table;
    String user;
    JScrollPane tableviewscroll;

    BookedPackagePanel() {
        setBorder(new EmptyBorder(0, 0, 0, 0));
        setBackground(Color.WHITE);
        this.setSize(1278, 900);
        setLayout(null);

        panel = new JPanel();
        panel.setBackground(new Color(32, 178, 170));
        panel.setBounds(7, 7, 1278, 160);
        add(panel);
        panel.setLayout(null);

        l1 = new JLabel("All Booked Packages");
        l1.setBounds(5, 55, 400, 45);
        panel.add(l1);
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("Segoe UI", Font.BOLD, 30));

        tableviewscroll = new JScrollPane();
        tableviewscroll.setBorder(new EmptyBorder(0, 0, 0, 0));
        tableviewscroll.setBounds(7, 172, 1271, 617);
        add(tableviewscroll);

        table = new JTable();
        table.setBorder(new LineBorder(Color.LIGHT_GRAY));
        table.getTableHeader().setBackground(new Color(32, 178, 170));
        table.getTableHeader().setForeground(Color.white);
        table.setSelectionBackground(new Color(240, 255, 255));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        table.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        table.setRowHeight(40);
        table.setDefaultEditor(Object.class, null);
        table.setCursor(new Cursor(Cursor.HAND_CURSOR));
        table.getTableHeader().setReorderingAllowed(false);
        tableviewscroll.setViewportView(table);

        loadData(null); // Load all data initially

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    String placeName = table.getValueAt(row, 0).toString();
                    String customerName = table.getValueAt(row, 1).toString();
                    String username = table.getValueAt(row, 2).toString();
                    String totalPersons = table.getValueAt(row, 3).toString();
                    String date = table.getValueAt(row, 4).toString();
                    String totalPrice = table.getValueAt(row, 5).toString();

                    StringBuilder details = new StringBuilder();
                    details.append("Booking Details:\n")
                            .append("Place Name: ").append(placeName).append("\n")
                            .append("Customer Name: ").append(customerName).append("\n")
                            .append("Username: ").append(username).append("\n")
                            .append("Total Persons: ").append(totalPersons).append("\n")
                            .append("Date: ").append(date).append("\n")
                            .append("Total Price: ").append(totalPrice).append("\n");

                    JOptionPane.showMessageDialog(null, details.toString());

                    int confirm = JOptionPane.showConfirmDialog(null,
                            "Do you want to cancel this booking?", "Cancel Booking", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        cancelBooking(username, placeName, totalPrice);
                    }
                }
            }
        });
    }

    public BookedPackagePanel(String user) {
        this();
        this.user = user;
        loadData(user);
    }

    private void loadData(String user) {
        try {
            Conn conn = new Conn();
            String sql = "SELECT place AS 'Place Name', name AS 'Customer Name', username AS 'Username', persons AS 'Total Persons', date AS 'Date', totalprice AS 'Total Price' FROM bookpackage";
            if (user != null) {
                sql += " WHERE username='" + user + "'";
            }
            PreparedStatement ps = conn.c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                table.setModel(DbUtils.resultSetToTableModel(rs));
            }
            setColumnWidths();
        } catch (Exception ae) {
            ae.printStackTrace();
        }
    }

    private void setColumnWidths() {
        table.getColumnModel().getColumn(0).setMaxWidth(200);
        table.getColumnModel().getColumn(1).setMaxWidth(230);
        table.getColumnModel().getColumn(2).setMaxWidth(200);
        table.getColumnModel().getColumn(3).setMaxWidth(200);
        table.getColumnModel().getColumn(4).setMaxWidth(200);
        table.getColumnModel().getColumn(5).setMaxWidth(241);
    }

    private void cancelBooking(String username, String placeName, String totalPrice) {
        try {
            Conn conn = new Conn();
            String sql = "DELETE FROM bookpackage WHERE username=? AND place=? AND totalprice=?";
            PreparedStatement ps = conn.c.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, placeName);
            ps.setString(3, totalPrice);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Booking for " + placeName + " canceled successfully.");
                loadData(this.user); // Refresh table after cancellation
            } else {
                JOptionPane.showMessageDialog(null, "Error: Booking not found or already deleted.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error! Please try again.");
        }
    }
}
