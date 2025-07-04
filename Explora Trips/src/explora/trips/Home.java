package explora.trips;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.Thread;

// The Home class extends JFrame to create a GUI window and implements Runnable for the slideshow
public class Home extends JFrame implements Runnable, ActionListener{
    
    public Login l; // Object for the Login page
    public AdminLogin a; // Object for the AdminLogin page
    
    // Arrays to store images for the slideshow
    ImageIcon i1 = null, i2 = null, i3 = null, i4 = null, i5 = null, i6 = null;
    ImageIcon[] image = new ImageIcon[]{i1, i2, i3, i4, i5, i6};
    
    Image j1 = null, j2 = null, j3 = null, j4 = null, j5 = null, j6 = null;
    Image[] jimage = new Image[]{j1, j2, j3, j4, j5, j6};
    
    ImageIcon i11 = null, i12 = null, i13 = null, i14 = null, i15 = null, i16 = null;
    ImageIcon[] iimage = new ImageIcon[]{i11, i12, i13, i14, i15, i16};
    
    // Labels for images and text
    JLabel l1, l2, l3, l4, l5, l6, l7, l8;
    JLabel[] jlabel = new JLabel[]{l1, l2, l3, l6, l7, l8};
    
    // Buttons for navigation
    JButton b1, b2, b3, btn;
    
    // Panels for organizing content
    JPanel contentPane, button, panel1;
    
    Thread t1; // Thread for running the slideshow
    
    public static void main(String[] args) {
        new Home().setVisible(true); // Start the GUI
    }
    
    // Constructor to initialize the GUI components
    Home() {
        super("Explora Trips"); // Set the title of the window
        
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the program when the window is closed
        
        // Main content panel
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        // Navigation bar panel
        button = new JPanel();
        button.setBackground(new Color(0, 139, 139, 220));
        button.setBounds(0, 0, 1540, 60);
        button.setLayout(null);
        contentPane.add(button);
        
        // Main panel for images
        panel1 = new JPanel();
        panel1.setBackground(Color.WHITE);
        panel1.setBounds(0, 60, 1540, 750);
        panel1.setLayout(null);
        contentPane.add(panel1);
        
        // Adding logo
        i4 = new ImageIcon(ClassLoader.getSystemResource("Travel/Management/System/icons/dd.jpeg"));
        Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        i6 = new ImageIcon(i5); 
        l4 = new JLabel(i6);
        l4.setBounds(5, 5, 50, 50);
        button.add(l4);
        
        // Adding title text
        JLabel l5 = new JLabel("DREAM DESTINATION TRIPS");
        l5.setForeground(Color.RED);
        l5.setFont(new Font("Times New Roman", Font.BOLD, 43));
        l5.setBounds(57, 2, 800, 57);
        button.add(l5);
       
        // Home button
        b1 = new JButton("Home");
        b1.setForeground(Color.WHITE);
        b1.setBackground(new Color(0, 139, 139, 220));
        b1.setBounds(1217, 7, 100, 46);
        b1.addActionListener(this);
        b1.setFocusable(false);
        button.add(b1);
        btn = b1;
        activeButton(b1);
       
        // Login button
        b2 = new JButton("Login");
        b2.setForeground(Color.WHITE);
        b2.setBackground(new Color(0, 139, 139, 220));
        b2.setBounds(1317, 7, 100, 46);
        b2.addActionListener(this);
        b2.setFocusable(false);
        button.add(b2);
        
        // Admin button
        b3 = new JButton("Admin");
        b3.setForeground(Color.WHITE);
        b3.setBackground(new Color(0, 139, 139, 220));
        b3.setBounds(1417, 7, 100, 46);
        b3.addActionListener(this);
        b3.setFocusable(false);
        button.add(b3);
        
        this.setVisible(true);
        panel1.setVisible(true);
        
        // Load slideshow images
        for(int i=0; i<=5; i++){
            this.image[i] = new ImageIcon(ClassLoader.getSystemResource("Travel/Management/System/icons/homepage"+(i+1)+".jpg"));
            this.jimage[i] = this.image[i].getImage().getScaledInstance(1540, 750,Image.SCALE_DEFAULT);
            this.iimage[i] = new ImageIcon(this.jimage[i]); 
            this.jlabel[i] = new JLabel(this.iimage[i]);
            this.jlabel[i].setBounds(0, 0, 1540, 750);
            panel1.add(this.jlabel[i]);
            
            // Adding welcome message
            JLabel l6 = new JLabel("Welcome to Explora Trips");
            l6.setForeground(Color.BLACK);
            l6.setFont(new Font("Bradley Hand ITC", Font.BOLD, 50));
            l6.setHorizontalAlignment(SwingConstants.CENTER);
            l6.setBounds(0, 10, 1540, 110);
            jlabel[i].add(l6);
        }
        
        // Start the slideshow thread
        t1 = new Thread(this);
        t1.start();
    }
    
    // Slideshow function
    public void run(){
        try{
            while(true){
                for(int i=0; i<=5; i++){
                    this.jlabel[i].setVisible(true);
                    Thread.sleep(2000);
                    this.jlabel[i].setVisible(false);
                }
            }
        }catch(Exception e){}
    }
    
    // Handling button clicks
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
            activeButton(b1);
            panel1.setVisible(true);
        }
        if(ae.getSource() == b2){
            activeButton(b2);
            l = new Login(this);
            l.setFocusable(true);
            l.setLocation(0,60);
            l.setVisible(true);
            contentPane.add(l);
        }
        if(ae.getSource() == b3){
            activeButton(b3);
            a = new AdminLogin(this);
            a.setFocusable(true);
            a.setLocation(0,60);
            a.setVisible(true);
            contentPane.add(a);
        }    
    }
    
    // Highlights the selected button
    public void activeButton(JButton b){
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(0, 139, 139, 220));
        btn = b;
        btn.setForeground(Color.CYAN);
        btn.setBackground(Color.BLACK);
        disable();
    }
    
    // Hides previously opened sections
    public void disable(){
        panel1.setVisible(false);
        if(l != null) l.setVisible(false);
        if(a != null) a.setVisible(false);
    }
}
