package explora.trips;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class AdminLogin extends JPanel implements ActionListener{

    // Panels for UI components
    JPanel p1, p2;
    // Text fields for input
    JTextField t1;
    JPasswordField passwordField;
    // Login button
    JButton b1;
    // Label for title
    JLabel l5;
    // Reference to Home frame
    Home home;
    
    AdminLogin(Home m) {
        
        this.home = m;
        setSize(1518,800);
        setBackground(Color.WHITE);
        setLayout(null);
    
        // Panel for login form
        p1 = new JPanel();
        p1.setBackground(new Color(80, 100, 182, 155)); // Semi-transparent blue background
        p1.setBounds(460, 230, 360, 250);
        p1.setLayout(null);
        add(p1);
        
        // Panel for admin image
        p2 = new JPanel();
        p2.setBounds(800, 220, 400, 260);
        p2.setBackground(Color.WHITE);
        p2.setLayout(null);
        add(p2);

        // Username label
        JLabel l1 = new JLabel("Username ");
        l1.setBounds(30, 20, 100, 25);
        l1.setFont(new Font("SAN_SERIF", Font.BOLD, 15));
        p1.add(l1);

        // Password label
        JLabel l2 = new JLabel("Password ");
        l2.setBounds(30, 90, 100, 25);
        l2.setFont(new Font("SAN_SERIF", Font.BOLD, 15));
        p1.add(l2);

        // Username text field
        t1 = new JTextField();
        t1.setBounds(30, 55, 280, 25);
        t1.setBorder(BorderFactory.createEmptyBorder());
        p1.add(t1);
    
        // Password field
        passwordField = new JPasswordField();
        passwordField.setBounds(30, 125, 280, 25);
        passwordField.setBorder(BorderFactory.createEmptyBorder());
        p1.add(passwordField);

        // Admin login image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Travel/Management/System/icons/adminlogin.png"));
        Image i2 = i1.getImage().getScaledInstance(400, 280, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0, 0, 400, 280);
        p2.add(l3);
        
        // Admin Login title
        l5 = new JLabel("ADMIN LOGIN");
        l5.setBounds(640, 160, 400, 50);
        l5.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 40));
        l5.setForeground(new Color(205, 92, 92)); // Dark red color
        l5.setBackground(Color.WHITE);
        add(l5);
        
        // Login button
        b1 = new JButton("Login");
        b1.addActionListener(this);
        b1.setForeground(Color.BLACK);
        b1.setBackground(Color.CYAN);
        b1.setBounds(30, 182, 280, 25);
        p1.add(b1);
    }
    
    // Action listener for login button
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
            try {
                Conn con = new Conn(); // Database connection
                String sql = "select * from adminlogin where username=? And password=?";
                PreparedStatement st = con.c.prepareStatement(sql);

                st.setString(1, t1.getText()); // Set username
                st.setString(2, passwordField.getText()); // Set password
                
                ResultSet rs = st.executeQuery();
                if (rs.next()) { // If valid credentials are found
                    new AdminHome(t1.getText()).setVisible(true); // Open admin home
                    home.dispose(); // Close home window
                } else{
                    JOptionPane.showMessageDialog(null, "Invalid Username Or Password!!!"); // Show error message
                }
            } catch (Exception e2) {
                e2.printStackTrace(); // Print error if any
            }
        }
    }
}
