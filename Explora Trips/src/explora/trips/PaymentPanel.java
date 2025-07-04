package explora.trips;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PaymentPanel extends JPanel {

    public PaymentPanel(CustomerHome parent, String user) {
        setLayout(null);
        setBounds(250, 75, 800, 600);
        setBackground(new Color(240, 240, 240)); // Light background

        // Gradient Background
        JPanel gradientPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(0, 102, 204), getWidth(), getHeight(), Color.WHITE);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        gradientPanel.setBounds(0, 0, 800, 600);
        gradientPanel.setLayout(null);
        add(gradientPanel);

        // Title Label
        JLabel label = new JLabel("Pay with Paytm");
        label.setFont(new Font("SansSerif", Font.BOLD, 38));
        label.setForeground(Color.WHITE);
        label.setBounds(200, 20, 400, 50);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        gradientPanel.add(label);

        // Paytm Image
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("travel/management/system/icons/paytm.jpeg"));
        Image i8 = i7.getImage().getScaledInstance(500, 300, Image.SCALE_SMOOTH);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel l4 = new JLabel(i9);
        l4.setBounds(150, 100, 500, 300);
        gradientPanel.add(l4);

        // Pay Button
        JButton pay = new JButton("Pay Now");
        styleButton(pay);
        pay.setBounds(250, 450, 140, 45);
        pay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Paytm().setVisible(true);
            }
        });
        gradientPanel.add(pay);

        // Back Button
        JButton back = new JButton("Back");
        styleButton(back);
        back.setBounds(420, 450, 140, 45);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        gradientPanel.add(back);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 102, 204));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        button.setBorderPainted(false);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 51, 153));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 102, 204));
            }
        });
    }
}
