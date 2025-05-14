package gui;

import modul.Log;

import javax.swing.*;
import java.awt.*;
import javax.swing.text.*;

public class WelcomeScreen extends JPanel {
    public static String signCardNumber = null;
    public static Integer signCardIndex = null;
    public WelcomeScreen(CardLayout layout, JPanel mainPanel) {
        Log.read();
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel box = new JPanel(new BorderLayout());
        box.setBackground(new Color(173, 216, 230));
        box.setPreferredSize(new Dimension(200, 200));
        box.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 4));

        JLabel title = new JLabel("BANKA", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        box.add(title, BorderLayout.CENTER);
        add(box, BorderLayout.CENTER);

        JLabel cardlabel = new JLabel("Číslo karty:");
        JTextField cardField = new JTextField();
        JButton loginButton = new JButton("⬆ Přiblížit se k bankomatu");

        Dimension size = new Dimension(250, 40);
        cardlabel.setSize(size);
        cardField.setPreferredSize(size);
        cardField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        ((AbstractDocument) cardField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string.matches("[0-9]*") && fb.getDocument().getLength() + string.length() <= 16) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text.matches("[0-9]*") && fb.getDocument().getLength() - length + text.length() <= 16) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });


        loginButton.setPreferredSize(size);
        loginButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
        loginButton.addActionListener(e -> {
            String amountText = cardField.getText();
            if (amountText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Prosím, čislo karty.", "Chyba", JOptionPane.ERROR_MESSAGE);
            } else if (amountText.length() != 16){
                JOptionPane.showMessageDialog(this, "Nespravně zadané čislo karty.", "Chyba", JOptionPane.ERROR_MESSAGE);
            } else {
                signCardNumber = amountText;
                signCardIndex = Log.cardNumberArrayList.indexOf(amountText);
                System.out.println("Index po přihlášení: " + WelcomeScreen.signCardIndex);

                layout.show(mainPanel, "PIN");
                cardField.setText("");
            }
        });

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.add(cardlabel);
        bottomPanel.add(cardField);
        bottomPanel.add(loginButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }
}
