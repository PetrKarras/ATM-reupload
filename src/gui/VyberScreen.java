package gui;

import modul.Log;
import modul.Transaknce;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VyberScreen extends JPanel {
    public VyberScreen(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new BorderLayout());
        setBackground(new Color(173, 216, 230));

        JLabel title = new JLabel("🏦 BANKA - Výběr", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        // Panel pro výběr částky
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        // Textové pole pro vlastní částku
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JTextField customAmountField = new JTextField(10);
        customAmountField.setPreferredSize(new Dimension(150, 30));
        customAmountField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        customAmountField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        JButton confirmCustomAmountButton = new JButton("Potvrdit");
        confirmCustomAmountButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        confirmCustomAmountButton.setBackground(new Color(30, 144, 255));
        confirmCustomAmountButton.setForeground(Color.WHITE);
        confirmCustomAmountButton.setFocusPainted(false);
        confirmCustomAmountButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        confirmCustomAmountButton.addActionListener(e -> {
            Integer vyber =Integer.parseInt( customAmountField.getText());
            if (vyber <= Log.zustatekArrayList.get(WelcomeScreen.signCardIndex)) {
               Transaknce.Vyber(WelcomeScreen.signCardIndex,vyber);
                cardLayout.show(mainPanel, "MENU");
            } else {
                JOptionPane.showMessageDialog(this, "Nedostatek prostředku.", "Chyba", JOptionPane.ERROR_MESSAGE);
            }
        });

        textPanel.add(new JLabel("Zadejte vlastní částku:"));
        textPanel.add(customAmountField);
        textPanel.add(confirmCustomAmountButton);

        // Panel pro tlačítka (3x2 mřížka)
        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        buttonPanel.setOpaque(false);

        // Předdefinované částky
        String[] amounts = {"500", "1000", "2000", "3000", "5000", "10000"};

        for (int i = 0; i < amounts.length; i++) {
            JButton btn = new JButton(amounts[i]);
            final String amount = amounts[i];
            btn.setFont(new Font("SansSerif", Font.PLAIN, 16));
            btn.setPreferredSize(new Dimension(80, 50));
            btn.setBackground(new Color(30, 144, 255));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

            btn.addActionListener(e -> {
                Integer vyber =Integer.parseInt( btn.getText());
                if (vyber <= Log.zustatekArrayList.get(WelcomeScreen.signCardIndex)) {
                    Transaknce.Vyber(WelcomeScreen.signCardIndex,vyber);
                    cardLayout.show(mainPanel, "MENU");
                } else {
                    JOptionPane.showMessageDialog(this, "Nedostatek prostředku.", "Chyba", JOptionPane.ERROR_MESSAGE);
                }
            });
            buttonPanel.add(btn);
        }

        // Uspořádání panelů
        centerPanel.add(textPanel, BorderLayout.NORTH);
        centerPanel.add(buttonPanel, BorderLayout.CENTER);

        // Přidání titulku a středového panelu na hlavní panel
        add(title, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }
}
