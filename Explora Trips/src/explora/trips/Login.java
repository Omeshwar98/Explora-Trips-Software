package explora.trips;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JPanel implements ActionListener {

    // Panels for structuring the UI
    JPanel p1, p2;
    JTextField t1; // Text field for username input
    JPasswordField passwordField; // Password field for secure input
    JButton b1, b2, b3; // Buttons for login, signup, and password recovery
    Home home; // Reference to the main Home frame
    JLabel l5; // Label for the login title
    
    // Default constructor
    Login() {
        
        setSize(1540,800); // Set panel size
        setBackground(Color.WHITE); // Set background color
        setLayout(null); // Absolute positioning
	
        // Panel for login form
        p1 = new JPanel();
        p1.setBackground(new Color(255, 255, 233));
        p1.setBounds(440, 230, 360, 273);
        p1.setLayout(null);
        add(p1);
        
        // Panel for login image
        p2 = new JPanel();
	p2.setBounds(800, 200, 400, 300);
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

        // Text field for username input
	t1 = new JTextField();
	t1.setBounds(30, 55, 280, 25);
        t1.setBorder(BorderFactory.createEmptyBorder());
	p1.add(t1);
	
        // Password field for secure input
	passwordField = new JPasswordField();
	passwordField.setBounds(30, 125, 280, 25);
        passwordField.setBorder(BorderFactory.createEmptyBorder());
	p1.add(passwordField);

	// Adding login image to the right panel
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Travel/Management/System/icons/login.jpg"));
        Image i2 = i1.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0, 20, 300, 300);
        p2.add(l3);
        
        // Title label for login
        l5 = new JLabel("LOGIN");
        l5.setBounds(680, 160, 400, 50);
        l5.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 40));
        l5.setForeground(new Color(205, 92, 92));
        l5.setBackground(Color.WHITE);
	add(l5);
        
        // Login button
	b1 = new JButton("Login");
	b1.addActionListener(this);
        b1.setForeground(new Color(46, 139, 87));
	b1.setBackground(new Color(176, 224, 230));
	b1.setBounds(30, 175, 120, 25);
	p1.add(b1);
		
        // Signup button
        b2 = new JButton("SignUp");
	b2.addActionListener(this);
	b2.setForeground(new Color(139, 69, 19));
	b2.setBackground(new Color(255, 235, 205));
	b2.setBounds(190, 175, 120, 25);
	p1.add(b2);

        // Forgot Password button
	b3 = new JButton("Forgot Password");
	b3.addActionListener(this);
	b3.setForeground(new Color(205, 92, 92));
	b3.setBackground(new Color(253, 245, 230));
	b3.setBounds(90, 225, 170, 25);
	p1.add(b3);
    }

    // Constructor to accept Home reference
    public Login(Home h) {
        this(); // Call default constructor
        this.home = h; // Assign Home reference
    }      
    
    // Event handling for button clicks
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) { // Login button
            try {
                Conn con = new Conn(); // Create database connection
                String sql = "SELECT * FROM account WHERE username=? AND password=?";
                PreparedStatement st = con.c.prepareStatement(sql);

                st.setString(1, t1.getText()); // Set username
                st.setString(2, passwordField.getText()); // Set password
                
                ResultSet rs = st.executeQuery(); // Execute query
                if (rs.next()) { // If credentials match
                    this.setVisible(false); // Hide login panel
                    new CustomerHome(t1.getText()).setVisible(true); // Open customer home page
                    home.dispose(); // Close home window
                 } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username Or Password!!!"); // Show error message
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        
        if (ae.getSource() == b2) { // Sign-up button
            Signup s = new Signup(); // Open Signup page
            l5.setVisible(false);
            p1.setVisible(false);
            p2.setVisible(false);
            s.setFocusable(true);
            this.add(s);
            s.setVisible(true);
        }   
        
        else if (ae.getSource() == b3) { // Forgot password button
            ForgotPassword f = new ForgotPassword(); // Open Forgot Password page
            l5.setVisible(false);
            p1.setVisible(false);
            p2.setVisible(false);
            f.setFocusable(true);
            this.add(f);
            f.setVisible(true);
        }
    }
}
