package gui;

import javax.swing.*;
import java.awt.*;

public class MenuScreen extends JPanel {
    public MenuScreen(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new BorderLayout());

        // Hlavní panel pro MenuScreen
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(173, 216, 230));

        // Horní panel s názvem a logout tlačítkem
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        JLabel title = new JLabel("🏦 BANKA", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 28));

        JButton logout = new JButton("❌");
        logout.setForeground(Color.RED);
        logout.setFont(new Font("SansSerif", Font.BOLD, 20));
        logout.addActionListener(e -> cardLayout.show(mainPanel, "WELCOME"));
        logout.setBackground(null);
        logout.setOpaque(false);
        logout.setContentAreaFilled(false);
        logout.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));


        topPanel.add(logout, BorderLayout.EAST);
        topPanel.add(title, BorderLayout.CENTER);

        // Panel pro tlačítka uprostřed obrazovky
        JPanel centerWrapper = new JPanel();
        centerWrapper.setOpaque(false);
        centerWrapper.setLayout(new BoxLayout(centerWrapper, BoxLayout.Y_AXIS));

        // Mřížka tlačítek s většími tlačítky
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        buttonPanel.setOpaque(false);
        buttonPanel.setMaximumSize(new Dimension(600, 300));  // Zvětšení pro větší tlačítka
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        String[] labels = {"Výběr", "Vklad", "Zůstatek", "Změna PINu"};
        String[] targets = {"VYBER", "VKLAD", "ZUSTATEK", "ZMENAPINU"};

        // Tlačítka s ikonami a většími fonty
        for (int i = 0; i < labels.length; i++) {
            JButton btn = new JButton(labels[i]);
            btn.setFont(new Font("SansSerif", Font.BOLD, 18));
            btn.setBackground(new Color(173, 216, 230));
            btn.setForeground(Color.BLACK);
            btn.setPreferredSize(new Dimension(150, 60));  // Zvětšení tlačítek pro lepší viditelnost
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
            final String target = targets[i];

            // Hover efekt (změna barvy při najetí myší)
            btn.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btn.setBackground(new Color(135, 206, 235));  // Světlejší modrá
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btn.setBackground(new Color(173, 216, 230));  // Původní barva
                }
            });

            btn.addActionListener(e -> cardLayout.show(mainPanel, target));
            buttonPanel.add(btn);
        }

        // Přidání BoxLayout pro středové umístění tlačítek
        centerWrapper.add(Box.createVerticalGlue());
        centerWrapper.add(buttonPanel);
        centerWrapper.add(Box.createVerticalGlue());

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(centerWrapper, BorderLayout.CENTER);

        add(panel, BorderLayout.CENTER);
    }
}
