package gui;

import modul.CardService;
import modul.UIService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PinChangeScreen extends JPanel {
    public PinChangeScreen(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new BorderLayout());
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(173, 216, 230));

        JLabel label = new JLabel("🔒 Zadejte nový PIN:", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.PLAIN, 20));

        JTextField pinField = new JTextField();
        pinField.setPreferredSize(new Dimension(200, 40));
        pinField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        pinField.setHorizontalAlignment(JTextField.CENTER);

        pinField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) || pinField.getText().length() >= 4) {
                    e.consume();
                }
            }
        });
        JButton backBtn = new JButton("🔙 Zpět");
        backBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        backBtn.setBackground(new Color(30, 144, 255));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        backBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        backBtn.addActionListener(e -> {
            UIService.Back(mainPanel,cardLayout,"MENU");});

        JButton acceptBtn = new JButton("✔ Změnit PIN");
        acceptBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        acceptBtn.setBackground(new Color(30, 144, 255));
        acceptBtn.setForeground(Color.WHITE);
        acceptBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        acceptBtn.setFocusPainted(false);
        acceptBtn.addActionListener(e -> {
            switch (CardService.PinChange(pinField.getText())) {
                case SUCCESS:
                    JOptionPane.showMessageDialog(mainPanel, "Pin byl úspěšně změněn.", "Informace", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case INVALID:
                    JOptionPane.showMessageDialog(mainPanel, "Neplatný formát PINu.", "Chyba", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        });

        JPanel center = new JPanel();
        center.setOpaque(false);
        center.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        center.add(label, gbc);
        gbc.gridy = 1;
        center.add(pinField, gbc);
        gbc.gridy = 2;
        center.add(acceptBtn, gbc);
        gbc.gridy =3;
        center.add(backBtn,gbc);

        panel.add(center, BorderLayout.CENTER);
        add(panel);
    }
}
