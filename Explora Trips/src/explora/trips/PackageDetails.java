package explora.trips;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.sql.*;

public class PackageDetails extends JPanel implements ActionListener {
    
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11;
    JButton b1, b2, bDelete; // Added delete button
    JPanel panel, panel1;
    JTextArea ta1;
    public AdminHome a;
    public CustomerHome c;
    String place, user;

    PackageDetails(AdminHome a, String place) {
        this();
        this.a = a;
        this.place = place;
        display();
        bDelete.setVisible(true); // Show delete button for admin
    }

    PackageDetails(CustomerHome c, String place, String user) {
        this();
        this.c = c;
        this.place = place;
        this.user = user;
        b1.setText("Book Package");
        bDelete.setVisible(false); // Hide delete button for user
        display();
    }

    PackageDetails() {
        setBorder(new EmptyBorder(0, 0, 0, 0));
        setBackground(Color.WHITE);
        this.setSize(1278, 900);
        setLayout(null);

        panel = new JPanel();
        panel.setBackground(new Color(32, 178, 170));
        panel.setBounds(7, 7, 1278, 160);
        add(panel);
        panel.setLayout(null);

        panel1 = new JPanel();
        panel1.setBackground(Color.WHITE);
        panel1.setBounds(7, 167, 1278, 740);
        add(panel1);
        panel1.setLayout(null);

        l11 = new JLabel("Package Details ");
        l11.setIcon(null);
        l11.setBounds(5, 55, 300, 45);
        panel.add(l11);
        l11.setBackground(new Color(32, 178, 170));
        l11.setForeground(Color.WHITE);
        l11.setFont(new Font("Segoe UI", Font.BOLD, 30));

        l1 = new JLabel("Place  ");
        l1.setBorder(new LineBorder(new Color(192, 192, 192)));
        l1.setBackground(new Color(255, 255, 255));
        l1.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
        l1.setBounds(2, 400, 274, 45);
        panel1.add(l1);

        l2 = new JLabel("No. Of Days and Nights  ");
        l2.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
        l2.setBorder(new LineBorder(new Color(192, 192, 192)));
        l2.setBackground(Color.WHITE);
        l2.setBounds(2, 444, 274, 45);
        panel1.add(l2);

        l3 = new JLabel();
        l3.setBorder(new LineBorder(new Color(192, 192, 192)));
        l3.setBackground(new Color(255, 255, 255));
        l3.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
        l3.setBounds(275, 400, 360, 45);
        panel1.add(l3);

        l4 = new JLabel();
        l4.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
        l4.setBorder(new LineBorder(new Color(192, 192, 192)));
        l4.setBackground(Color.WHITE);
        l4.setBounds(275, 444, 360, 45);
        panel1.add(l4);

        l5 = new JLabel("City/State  ");
        l5.setBorder(new LineBorder(new Color(192, 192, 192)));
        l5.setBackground(new Color(255, 255, 255));
        l5.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
        l5.setBounds(634, 400, 274, 45);
        panel1.add(l5);

        l6 = new JLabel("Price  ");
        l6.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
        l6.setBorder(new LineBorder(new Color(192, 192, 192)));
        l6.setBackground(Color.WHITE);
        l6.setBounds(634, 444, 274, 45);
        panel1.add(l6);

        l7 = new JLabel();
        l7.setBorder(new LineBorder(new Color(192, 192, 192)));
        l7.setBackground(new Color(255, 255, 255));
        l7.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
        l7.setBounds(907, 400, 360, 45);
        panel1.add(l7);

        l8 = new JLabel();
        l8.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
        l8.setBorder(new LineBorder(new Color(192, 192, 192)));
        l8.setBackground(Color.WHITE);
        l8.setBounds(907, 444, 360, 45);
        panel1.add(l8);

        l9 = new JLabel("Description  ");
        l9.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
        l9.setBorder(new LineBorder(new Color(192, 192, 192)));
        l9.setBackground(Color.WHITE);
        l9.setBounds(55, 488, 274, 105);
        panel1.add(l9);

        ta1 = new JTextArea();
        ta1.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
        ta1.setBorder(new LineBorder(new Color(192, 192, 192)));
        ta1.setBackground(Color.WHITE);
        ta1.setBounds(328, 488, 885, 105);
        ta1.setLineWrap(true);
        panel1.add(ta1);

        l10 = new JLabel();
        l10.setBounds(385, 20, 500, 360);
        l10.setBorder(new LineBorder(new Color(192, 192, 192)));
        panel1.add(l10);

        b1 = new JButton("Edit Package");
        b1.setBorder(new EmptyBorder(0, 0, 0, 0));
        b1.setBounds(1110, 120, 140, 30);
        panel.add(b1);
        b1.addActionListener(this);
        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b1.setFocusable(false);
        b1.setForeground(new Color(0, 128, 128));
        b1.setFont(new Font("Segoe UI", Font.BOLD, 15));
        b1.setBackground(Color.WHITE);

        bDelete = new JButton("Delete Package"); // Initialize delete button
        bDelete.setBorder(new EmptyBorder(0, 0, 0, 0));
        bDelete.setBounds(950, 120, 140, 30);
        panel.add(bDelete);
        bDelete.addActionListener(this);
        bDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bDelete.setFocusable(false);
        bDelete.setForeground(Color.RED);
        bDelete.setFont(new Font("Segoe UI", Font.BOLD, 15));
        bDelete.setBackground(Color.WHITE);

        b2 = new JButton("Back", new ImageIcon(ClassLoader.getSystemResource("Travel/Management/System/icons/back.png")));
        b2.setBorder(new EmptyBorder(0, 0, 0, 0));
        b2.setBounds(20, 120, 140, 30);
        panel.add(b2);
        b2.setHorizontalAlignment(SwingConstants.LEFT);
        b2.addActionListener(this);
        b2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b2.setFocusable(false);
        b2.setForeground(Color.WHITE);
        b2.setFont(new Font("Segoe UI", Font.BOLD, 15));
        b2.setBackground(new Color(32, 178, 170));
    }

    public void display() {
        try {
            Conn conn = new Conn();
            PreparedStatement ps = conn.c.prepareStatement("SELECT * FROM package WHERE place=?");
            ps.setString(1, place);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                l3.setText(rs.getString(1)); // Place
                l4.setText(rs.getString(4)); // Days and Nights
                l7.setText(rs.getString(2)); // City/State
                l8.setText(rs.getString(3)); // Price
                ta1.setText(rs.getString(5)); // Description
                byte[] photo = rs.getBytes(6);
                Image i1 = new ImageIcon(photo).getImage().getScaledInstance(500, 360, Image.SCALE_SMOOTH);
                l10.setIcon(new ImageIcon(i1));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace for debugging
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            if (a != null) {
                new AddPackage(this, place).setVisible(true);
            }
            if (c != null) {
                new BookPackage(place, user).setVisible(true);
            }
        } else if (ae.getSource() == b2) {
            panel.setVisible(false);
            panel1.setVisible(false);
            if (a != null) {
                PackagePanel pp = new PackagePanel(a);
                add(pp);
                pp.setVisible(true);
            }
            if (c != null) {
                PackagePanel pp = new PackagePanel(c, user);
                add(pp);
                pp.setVisible(true);
            }
        } else if (ae.getSource() == bDelete) { // Handle delete button action
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this package?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    Conn conn = new Conn();
                    PreparedStatement ps = conn.c.prepareStatement("DELETE FROM package WHERE place=?");
                    ps.setString(1, place);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Package deleted successfully.");
                    // Optionally, navigate back to the package panel after deletion
                    panel.setVisible(false);
                    panel1.setVisible(false);
                    PackagePanel pp = new PackagePanel(a); // Assuming admin is viewing
                    add(pp);
                    pp.setVisible(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error deleting package.");
                }
            }
        }
    }
}