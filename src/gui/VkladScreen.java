package gui;

import modul.Transaknce;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VkladScreen extends JPanel {
    public VkladScreen(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new BorderLayout());
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(173, 216, 230));

        // Titulek
        JLabel label = new JLabel("💵 Vložte částku:", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.PLAIN, 20));

        // Vstupní pole pro částku
        JTextField field = new JTextField();
        field.setPreferredSize(new Dimension(200, 40)); // Zmenšeno na přiměřenou šířku
        field.setHorizontalAlignment(JTextField.CENTER);
        field.setFont(new Font("SansSerif", Font.PLAIN, 18)); // Zvýšení velikosti písma

        // Validace pro vstup - pouze čísla
        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume(); // Zamezí zadání nečíselných znaků
                }
            }
        });

        // Tlačítko pro potvrzení
        JButton acceptBtn = new JButton("✔ Vložit");
        acceptBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        acceptBtn.setBackground(new Color(30, 144, 255)); // Modrá barva
        acceptBtn.setForeground(Color.WHITE); // Bílé písmo
        acceptBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding tlačítka
        acceptBtn.setFocusPainted(false); // Vypnutí ohraničení při kliknutí
        acceptBtn.addActionListener(e -> {
            Integer vklad =Integer.parseInt( field.getText());
            if (vklad >0 ) {
                Transaknce.Vklad(WelcomeScreen.signCardIndex,vklad);
                cardLayout.show(mainPanel, "MENU");
                JOptionPane.showMessageDialog(this, "Vklad byl uspěšný.", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Prosím, zadejte částku.", "Chyba", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Panel pro centrum obrazovky
        JPanel center = new JPanel();
        center.setOpaque(false);
        center.setLayout(new GridBagLayout());  // Používáme GridBagLayout pro lepší rozvržení
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);  // Vytvoření mezer mezi komponentami

        center.add(label, gbc);  // Titulek

        gbc.gridy = 1;
        center.add(field, gbc);  // Textové pole

        gbc.gridy = 2;
        center.add(acceptBtn, gbc);  // Tlačítko pro vložení

        panel.add(center, BorderLayout.CENTER);
        add(panel);
    }
}
