package gui;

import modul.UIService;

import javax.swing.*;
import java.awt.*;

public class MenuScreen extends JPanel {
    public MenuScreen(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(173, 216, 230));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        JLabel title = new JLabel("🏦 BANKA", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 28));

        JButton logout = new JButton("❌");
        logout.setForeground(Color.RED);
        logout.setFont(new Font("SansSerif", Font.BOLD, 20));
        logout.addActionListener(e -> UIService.Back(mainPanel,cardLayout,"WELCOME"));
        logout.setBackground(null);
        logout.setOpaque(false);
        logout.setContentAreaFilled(false);
        logout.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));


        topPanel.add(logout, BorderLayout.EAST);
        topPanel.add(title, BorderLayout.CENTER);

        JPanel centerWrapper = new JPanel();
        centerWrapper.setOpaque(false);
        centerWrapper.setLayout(new BoxLayout(centerWrapper, BoxLayout.Y_AXIS));

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        buttonPanel.setOpaque(false);
        buttonPanel.setMaximumSize(new Dimension(600, 300));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        String[] labels = {"Výběr", "Vklad", "Zůstatek", "Změna PINu"};
        String[] targets = {"VYBER", "VKLAD", "ZUSTATEK", "ZMENAPINU"};

        for (int i = 0; i < labels.length; i++) {
            JButton btn = new JButton(labels[i]);
            btn.setFont(new Font("SansSerif", Font.BOLD, 18));
            btn.setBackground(new Color(173, 216, 230));
            btn.setForeground(Color.BLACK);
            btn.setPreferredSize(new Dimension(150, 60));
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
            final String target = targets[i];

            btn.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btn.setBackground(new Color(135, 206, 235));
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btn.setBackground(new Color(173, 216, 230));
                }
            });

            btn.addActionListener(e ->{
                        UIService.clearTextFields(mainPanel);
                        cardLayout.show(mainPanel, target);
                    });



            buttonPanel.add(btn);
        }

        centerWrapper.add(Box.createVerticalGlue());
        centerWrapper.add(buttonPanel);
        centerWrapper.add(Box.createVerticalGlue());

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(centerWrapper, BorderLayout.CENTER);

        add(panel, BorderLayout.CENTER);
    }
}
