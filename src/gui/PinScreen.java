package gui;

import modul.Log;

import javax.swing.*;
import java.awt.*;
import javax.swing.text.*;

public class PinScreen extends JPanel {
    private  Integer pocetPokusu =3;
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

        // PIN TextField s maximálním počtem 4 číslic, maskováním PINu
        JPasswordField pinField = new JPasswordField(10);
        pinField.setPreferredSize(new Dimension(120, 30));
        pinField.setEchoChar('*');  // Zobrazování PINu jako hvězdičky

        // Použití DocumentFilter pro omezení délky na 4 číslice
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
            String amountText = pinField.getText();
            pinField.setText("");
            if (pocetPokusu == 0) {
                JOptionPane.showMessageDialog(this, "Příliš mnoho pokusů. Zpět na úvodní obrazovku.", "Chyba", JOptionPane.ERROR_MESSAGE);
                cardLayout.show(mainPanel, "WELCOME");
                pocetPokusu=3;
                return;
            }
            if (amountText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Zadejte pin.", "Chyba", JOptionPane.ERROR_MESSAGE);
                pocetPokusu--;
            } else if (amountText.length() != 4) {
                JOptionPane.showMessageDialog(this, "Nesprávný formát PINu", "Chyba", JOptionPane.ERROR_MESSAGE);
                pocetPokusu--;
            } else if (WelcomeScreen.signCardIndex == -1) {
                Log.newWrite(WelcomeScreen.signCardNumber, amountText);
                JOptionPane.showMessageDialog(this, "Nová karta registrována", "Registrace", JOptionPane.INFORMATION_MESSAGE);
                pocetPokusu=3;
                cardLayout.show(mainPanel, "MENU");
                WelcomeScreen.signCardIndex = Log.pinArrayList.indexOf(amountText);
            } else if (!amountText.equals(Log.pinArrayList.get(WelcomeScreen.signCardIndex))) {
                JOptionPane.showMessageDialog(this, "Nesprávný PIN", "Chyba", JOptionPane.ERROR_MESSAGE);
                pocetPokusu--;
            } else {
                pocetPokusu=3;
                cardLayout.show(mainPanel, "MENU");
                WelcomeScreen.signCardIndex = Log.pinArrayList.indexOf(amountText);
            }

            System.out.println("Zbývající pokusy: " + pocetPokusu);
        });


        inputPanel.add(pinLabel);
        inputPanel.add(pinField);
        inputPanel.add(confirm);

        // Přidání všech komponent
        add(Box.createVerticalGlue());
        add(title);
        add(Box.createVerticalStrut(20));
        add(inputPanel);
        add(Box.createVerticalGlue());
    }
}
