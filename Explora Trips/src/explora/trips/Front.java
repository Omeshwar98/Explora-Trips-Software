package explora.trips;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Front extends JFrame implements ActionListener {

    private JButton b1; // Button to proceed to the next screen
    private JLabel l2; // Label for the title text
    private Timer timer; // Timer for blinking effect on the title
    private boolean isTitleVisible = true; // Flag to toggle title visibility

    public Front() {
        super("Explora Trips"); // S  super("Exetting the title of the JFrame

        setBounds(90, 60, 1350, 700); // Setting the window position and size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
        setResizable(false); // Prevent resizing
        setLayout(null); // Absolute layout positioning

        // Background Image Panel
        JLabel background = new JLabel();
        background.setBounds(0, 0, 1350, 700);
        ImageIcon imgIcon = new ImageIcon(ClassLoader.getSystemResource("Travel/Management/System/icons/frontpage.jpg"));
        Image img = imgIcon.getImage().getScaledInstance(1350, 700, Image.SCALE_SMOOTH);
        background.setIcon(new ImageIcon(img));
        add(background);

        // Title Label
        l2 = new JLabel("Explora Trips");
        l2.setBounds(50, 15, 1345, 100);
        l2.setFont(new Font("Arial", Font.ITALIC, 50));
        l2.setForeground(Color.ORANGE);
        background.add(l2);

        // Next Button with Modern Style
        b1 = new JButton("Next");
        b1.setBounds(590, 600, 170, 50);
        b1.setFont(new Font("Arial", Font.BOLD, 20));
        b1.setBackground(new Color(255, 140, 0)); // Orange color
        b1.setForeground(Color.WHITE);
        b1.setFocusPainted(false);
        b1.setBorder(BorderFactory.createEmptyBorder()); // Removes border
        b1.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Changes cursor to hand
        b1.addActionListener(this); // Adding action listener to button
        background.add(b1);

        // Button Hover Effect
        b1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                b1.setBackground(new Color(255, 69, 0)); // Darker orange on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                b1.setBackground(new Color(255, 140, 0)); // Original color
            }
        });

        // Title Blinking Effect Using Timer
        timer = new Timer(500, e -> {
            isTitleVisible = !isTitleVisible;
            l2.setVisible(isTitleVisible);
        });
        timer.start(); // Starts the blinking effect

        setVisible(true); // Make the frame visible
    }

    // Handles button click event
    public void actionPerformed(ActionEvent ae) {
        this.setVisible(false); // Hides the current frame
        timer.stop(); // Stops the blinking effect
        new Home().setVisible(true); // Opens the Home screen
    }

    public static void main(String[] args) {
        new Front(); // Launches the application
    }
}