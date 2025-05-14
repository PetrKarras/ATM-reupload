package gui;

import modul.Transaknce;

import javax.swing.*;
import java.awt.*;

public class ZustatekScreen extends JPanel {
    private JLabel label;

    public ZustatekScreen(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new BorderLayout());
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(173, 216, 230));

        label = new JLabel("Zůstatek bude zobrazen po potvrzení.", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 24));
        label.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JButton acceptBtn = new JButton("👁️ acceptBtn odhalení osobních informací");
        acceptBtn.setFont(new Font("SansSerif", Font.PLAIN, 16));
        acceptBtn.setBackground(new Color(255, 140, 0));
        acceptBtn.setForeground(Color.WHITE);
        acceptBtn.setFocusPainted(false);
        acceptBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        acceptBtn.addActionListener(e -> {
            if (WelcomeScreen.signCardIndex != null && WelcomeScreen.signCardIndex >= 0) {
                label.setText(Transaknce.Vypis(WelcomeScreen.signCardIndex));
            } else {
                label.setText("Chyba: Karta není načtena.");
            }
        });

        JButton backBtn = new JButton("🔙 Zpět");
        backBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        backBtn.setBackground(new Color(30, 144, 255));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        backBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        backBtn.addActionListener(e -> {
            reset();  // Resetuj stav při návratu
            cardLayout.show(mainPanel, "MENU");
        });

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.setOpaque(false);
        bottomPanel.add(acceptBtn);
        bottomPanel.add(backBtn);

        panel.add(label, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);
        add(panel);
    }

    public void reset() {
        label.setText("Zůstatek bude zobrazen po potvrzení.");
    }
}
