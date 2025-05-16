package gui;

import modul.UIService;

import javax.swing.*;
import java.awt.*;
import javax.swing.text.*;

public class PinScreen extends JPanel {
    public PinScreen(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(173, 216, 230));

        JLabel title = new JLabel("🏦 BANKA", SwingConstants.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        inputPanel.setOpaque(false);

        JLabel pinLabel = new JLabel("Zadejte PIN:");
        pinLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JPasswordField pinField = new JPasswordField(10);
        pinField.setPreferredSize(new Dimension(120, 30));
        pinField.setEchoChar('*');

        ((AbstractDocument) pinField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string.matches("[0-9]*") && fb.getDocument().getLength() + string.length() <= 4) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text.matches("[0-9]*") && fb.getDocument().getLength() + text.length() <= 4) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        JButton confirm = new JButton("✔");
        confirm.setFont(new Font("SansSerif", Font.BOLD, 16));
        confirm.setBackground(new Color(30, 144, 255)); // Nastavení barvy pozadí
        confirm.setForeground(Color.WHITE); // Nastavení barvy textu
        confirm.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Zaoblení rohů
        confirm.setFocusPainted(false); // Vypnutí ohraničení při kliknutí


        confirm.addActionListener(e -> {
            UIService.PinConfirm(pinField.getText(),mainPanel,cardLayout);});

        JButton backBtn = new JButton("🔙 Zpět");
        backBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        backBtn.setBackground(new Color(30, 144, 255));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        backBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        backBtn.addActionListener(e -> {
            UIService.clearTextFields(mainPanel);
            UIService.Back(mainPanel,cardLayout,"WELCOME");});


        inputPanel.add(pinLabel);
        inputPanel.add(pinField);
        inputPanel.add(confirm);
        inputPanel.add(backBtn);

        add(Box.createVerticalGlue());
        add(title);
        add(Box.createVerticalStrut(20));
        add(inputPanel);
        add(Box.createVerticalGlue());
    }
}
