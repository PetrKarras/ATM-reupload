package gui;

import modul.CardService;
import modul.UIService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VkladScreen extends JPanel {
    public VkladScreen(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new BorderLayout());
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(173, 216, 230));

        JLabel label = new JLabel("💵 Vložte částku:", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.PLAIN, 20));

        JTextField customAmountField = new JTextField();
        customAmountField.setPreferredSize(new Dimension(200, 40));
        customAmountField.setHorizontalAlignment(JTextField.CENTER);
        customAmountField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        customAmountField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });

        JButton acceptBtn = new JButton("✔ Vložit");
        acceptBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        acceptBtn.setBackground(new Color(30, 144, 255));
        acceptBtn.setForeground(Color.WHITE);
        acceptBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        acceptBtn.setFocusPainted(false);
        acceptBtn.addActionListener(e -> {
            switch (CardService.Vklad(customAmountField.getText())) {
                case SUCCESS:
                    JOptionPane.showMessageDialog(mainPanel, "Vklad byl úspěšný.", "Informace", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case INSUFFICIENT:
                    JOptionPane.showMessageDialog(mainPanel, "Nejde vložit 0 kč", "Chyba", JOptionPane.ERROR_MESSAGE);
                    break;
                case INVALID:
                    JOptionPane.showMessageDialog(mainPanel, "Textové pole je prázdné. Prosím zadejte častku vkladu.", "Chyba", JOptionPane.ERROR_MESSAGE);
                    break;
                case TOO_MUCH:
                    JOptionPane.showMessageDialog(mainPanel, "Číslo je moc velké.", "Chyba", JOptionPane.ERROR_MESSAGE);
                    break;
            }});

        JButton backBtn = new JButton("🔙 Zpět");
        backBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        backBtn.setBackground(new Color(30, 144, 255));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        backBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        backBtn.addActionListener(e -> {
            UIService.Back(mainPanel,cardLayout,"MENU");});

        JPanel center = new JPanel();
        center.setOpaque(false);
        center.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        center.add(label, gbc);

        gbc.gridy = 1;
        center.add(customAmountField, gbc);
        gbc.gridy = 2;
        center.add(acceptBtn, gbc);
        gbc.gridy =3;
        center.add(backBtn,gbc);

        panel.add(center, BorderLayout.CENTER);
        add(panel);
    }
}
