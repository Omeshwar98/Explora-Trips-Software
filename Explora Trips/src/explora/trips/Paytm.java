package explora.trips;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;

public class Paytm extends JFrame {
    private static final String UPI_ID = "7690010449@pthdfc";
    private static final String PAYTM_URL = "https://paytm.com/rent-payment";

    public Paytm() {
        setTitle("Payment Options");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 450);
        setLocation(600, 220);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(200, 230, 250));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel titleLabel = new JLabel("<html><h1 style='color:#2E86C1;'>Payment Options</h1></html>");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);

        JLabel upiLabel = new JLabel("<html><p style='font-size:16px;color:#34495E;'>UPI ID: <b>" + UPI_ID + "</b></p></html>");
        upiLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(upiLabel);

        JButton showQRButton = createStyledButton("Show QR Code");
        showQRButton.addActionListener(e -> showQRCodePanel());
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(showQRButton);

        JButton payButton = createStyledButton("Pay With Paytm");
        payButton.addActionListener(e -> openWebPageWithUPI());
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(payButton);

        JButton backButton = createStyledButton("Back");
        backButton.addActionListener(e -> setVisible(false));
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(backButton);

        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(220, 50));
        button.setMaximumSize(new Dimension(220, 50));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(new Color(41, 128, 185));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void showQRCodePanel() {
        JDialog qrDialog = new JDialog(this, "Scan QR Code", true);
        qrDialog.setSize(300, 350);
        qrDialog.setLocationRelativeTo(this);

        JPanel qrPanel = new JPanel();
        qrPanel.setLayout(new BoxLayout(qrPanel, BoxLayout.Y_AXIS));
        qrPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        qrPanel.setBackground(Color.WHITE);

        JLabel instructionLabel = new JLabel("Scan the QR Code below:");
        instructionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        qrPanel.add(instructionLabel);
        qrPanel.add(Box.createVerticalStrut(10));

        java.net.URL qrCodeURL = getClass().getResource("/travel/management/system/icons/qrcode.png");
        if (qrCodeURL != null) {
            ImageIcon qrIcon = new ImageIcon(qrCodeURL);
            Image qrImage = qrIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            JLabel qrLabel = new JLabel(new ImageIcon(qrImage));
            qrLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            qrPanel.add(qrLabel);
        } else {
            JLabel errorLabel = new JLabel("QR Code not found!");
            errorLabel.setForeground(Color.RED);
            errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            qrPanel.add(errorLabel);
        }

        qrDialog.add(qrPanel);
        qrDialog.setVisible(true);
    }

    private void openWebPageWithUPI() {
        try {
            Path tempFile = Files.createTempFile("upi_payment", ".html");
            String htmlContent = generateHtmlContent();
            Files.write(tempFile, htmlContent.getBytes());
            Desktop.getDesktop().browse(tempFile.toUri());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to open payment page!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String generateHtmlContent() {
        return "<html>" +
                "<head><meta charset='UTF-8'><title>UPI Payment</title>" +
                "<style>" +
                "body { font-family: Arial, sans-serif; text-align: center; padding: 50px; background: url('https://wallpaperaccess.com/full/13236733.jpg') no-repeat center center fixed; background-size: cover; }" +
                "body { font-family: Arial, sans-serif; text-align: center; padding: 50px; background-color: #e3f2fd; }" +
                ".upi-box { font-size: 22px; margin-bottom: 20px; font-weight: bold; color: #0078D4; }" +
                ".pay-button { background-color: #0078D4; color: white; padding: 12px 25px; font-size: 20px; border-radius: 10px; border: none; cursor: pointer; }" +
                ".pay-button:hover { background-color: #0056b3; }" +
                "</style></head><body>" +
                "<h2>Proceed with UPI Payment</h2>" +
                "<div class='upi-box'>UPI ID: <b>" + UPI_ID + "</b></div>" +
                "<button class='pay-button' onclick=\"window.location.href='" + PAYTM_URL + "'\">Proceed to Paytm</button>" +
                "</body></html>";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Paytm::new);
    }
}