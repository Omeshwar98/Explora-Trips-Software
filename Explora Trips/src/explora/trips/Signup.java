package explora.trips;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

// Signup class for user registration
public class Signup extends JPanel implements ActionListener {

    // Panels for layout
    JPanel p1, p2;
    // Text fields for user input
    JTextField t1, t2, t3, t4;
    // Buttons for actions
    JButton b1, b2;
    // Dropdown for security question selection
    JComboBox c1;
    // Label for title
    JLabel l5;

    // Constructor for setting up the signup form
    Signup() {
        
        setSize(1518, 800); // Set panel size
        setBackground(Color.WHITE); // Set background color
        setLayout(null);
        
        // Panel for user input fields
        p1 = new JPanel();
        p1.setBounds(440, 230, 370, 273);
        p1.setBackground(new Color(100, 250, 150)); // Light green background
        p1.setLayout(null);
        add(p1);
        
        // Panel for displaying an image
        p2 = new JPanel();
        p2.setBounds(810, 200, 300, 330);
        p2.setBackground(Color.WHITE);
        p2.setLayout(null);
        add(p2);
        
        // Labels for form fields
        JLabel l = new JLabel("Username :");
        l.setForeground(Color.DARK_GRAY);
        l.setFont(new Font("Tahoma", Font.BOLD, 14));
        l.setBounds(30, 20, 140, 30);
        p1.add(l);

        JLabel ll = new JLabel("Name :");
        ll.setForeground(Color.DARK_GRAY);
        ll.setFont(new Font("Tahoma", Font.BOLD, 14));
        ll.setBounds(30, 60, 140, 30);
        p1.add(ll);

        JLabel l2 = new JLabel("Password :");
        l2.setForeground(Color.DARK_GRAY);
        l2.setFont(new Font("Tahoma", Font.BOLD, 14));
        l2.setBounds(30, 100, 140, 30);
        p1.add(l2);

        JLabel l3 = new JLabel("Security Question :");
        l3.setForeground(Color.DARK_GRAY);
        l3.setFont(new Font("Tahoma", Font.BOLD, 14));
        l3.setBounds(30, 140, 140, 30);
        p1.add(l3);
        
        JLabel l4 = new JLabel("Answer :");
        l4.setForeground(Color.DARK_GRAY);
        l4.setFont(new Font("Tahoma", Font.BOLD, 14));
        l4.setBounds(30, 180, 140, 30);
        p1.add(l4);
         
        // Title label
        l5 = new JLabel("CREATE ACCOUNT");
        l5.setBounds(580, 160, 400, 50);
        l5.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 40));
        l5.setForeground(new Color(205, 92, 92));
        l5.setBackground(Color.WHITE);
        add(l5);
        
        // Dropdown menu for security questions
        c1 = new JComboBox(new String[] { "Your NickName?", "Your Lucky Number?", "Your Favourite Anime?", "Your Childhood Name?" });
        c1.setBounds(180, 148, 155, 20);
        c1.setBackground(Color.WHITE);
        p1.add(c1);
        
        // Image for signup panel
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Travel/Management/System/icons/signup.png"));
        Image i2 = i1.getImage().getScaledInstance(295, 280, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l6 = new JLabel(i3);
        l6.setBounds(0, 24, 295, 280);
        p2.add(l6);

        // Input fields
        t1 = new JTextField(); // Username
        t1.setBounds(180, 27, 155, 20);
        t1.setBorder(BorderFactory.createEmptyBorder());
        p1.add(t1);
    
        t2 = new JTextField(); // Name
        t2.setBounds(180, 68, 155, 20);
        t2.setBorder(BorderFactory.createEmptyBorder());
        p1.add(t2);

        t3 = new JTextField(); // Password
        t3.setBounds(180, 107, 155, 20);
        t3.setBorder(BorderFactory.createEmptyBorder());
        p1.add(t3);

        t4 = new JTextField(); // Security answer
        t4.setBounds(180, 187, 155, 20);
        t4.setBorder(BorderFactory.createEmptyBorder());
        p1.add(t4);

        // Buttons
        b1 = new JButton("Create"); // Create account button
        b1.addActionListener(this);
        b1.setFont(new Font("Tahoma", Font.BOLD, 13));
        b1.setBounds(50, 225, 120, 25);
        b1.setBackground(new Color(176, 224, 230));
        b1.setForeground(new Color(139, 69, 19));
        p1.add(b1);

        b2 = new JButton("Back"); // Back button
        b2.setFont(new Font("Tahoma", Font.BOLD, 13));
        b2.setBounds(190, 225, 120, 25);
        b2.setBackground(new Color(255, 235, 205));
        b2.setForeground(new Color(46, 139, 87));
        b2.addActionListener(this);
        p1.add(b2);
    }
    
    // Action listener for button clicks
    public void actionPerformed(ActionEvent ae) {
        try {
            Conn con = new Conn(); // Database connection
            
            if (ae.getSource() == b1) { // Create account
                String sql = "insert into account(username, name, password, security, answer) values(?,?,?,?,?)"; 
                PreparedStatement st = con.c.prepareStatement(sql);

                st.setString(1, t1.getText());
                st.setString(2, t2.getText());
                st.setString(3, t3.getText());
                st.setString(4, (String) c1.getSelectedItem());
                st.setString(5, t4.getText());
                 
                // Check if username already exists
                String pr = "select * from account where username=?";
                PreparedStatement st1 = con.c.prepareStatement(pr);
                st1.setString(1, t1.getText());
                
                ResultSet rs = st1.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Username Already Exists!!!");
                } else {
                    st.executeUpdate(); // Insert new account
                    JOptionPane.showMessageDialog(null, "Account Created Successfully");
                    
                    // Clear fields
                    t1.setText("");
                    t2.setText("");
                    t3.setText("");
                    t4.setText("");
                }
            } else if (ae.getSource() == b2) { // Navigate back to login
                Login n = new Login();
                l5.setVisible(false);
                p1.setVisible(false);
                p2.setVisible(false);
                n.setFocusable(true);
                this.add(n);
                n.setVisible(true);
            }
        } catch (Exception e) {}
    }
}