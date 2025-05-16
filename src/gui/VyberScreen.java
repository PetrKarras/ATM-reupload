package gui;

import modul.CardService;
import modul.Result;
import modul.UIService;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;

public class VyberScreen extends JPanel {
    public VyberScreen(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new BorderLayout());
        setBackground(new Color(173, 216, 230));

        JLabel title = new JLabel("🏦 BANKA - Výběr", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));


        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());


        JPanel textPanel = new JPanel();
        textPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JTextField customAmountField = new JTextField(10);
        customAmountField.setPreferredSize(new Dimension(150, 30));
        customAmountField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        customAmountField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));((AbstractDocument) customAmountField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string.matches("[0-9]*")) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text.matches("[0-9]*")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        JButton confirmCustomAmountButton = new JButton("Potvrdit");
        confirmCustomAmountButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        confirmCustomAmountButton.setBackground(new Color(30, 144, 255));
        confirmCustomAmountButton.setForeground(Color.WHITE);
        confirmCustomAmountButton.setFocusPainted(false);
        confirmCustomAmountButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        confirmCustomAmountButton.addActionListener(e -> {
            switch (CardService.Vyber(customAmountField.getText())) {
                case SUCCESS:
                    JOptionPane.showMessageDialog(mainPanel, "Výběr byl úspěšný.", "Informace", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case INSUFFICIENT:
                    JOptionPane.showMessageDialog(mainPanel, "Nedostatek prostředků na účtu.", "Chyba", JOptionPane.ERROR_MESSAGE);
                    break;
                case INVALID:
                    JOptionPane.showMessageDialog(mainPanel, "Neplatná částka k výběru.", "Chyba", JOptionPane.ERROR_MESSAGE);
                    break;
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

        textPanel.add(new JLabel("Zadejte vlastní částku:"));
        textPanel.add(customAmountField);
        textPanel.add(confirmCustomAmountButton);
        textPanel.add(backBtn);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        buttonPanel.setOpaque(false);

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
            btn.addActionListener(e -> {CardService.Vyber(btn.getText());});
            buttonPanel.add(btn);
        }

        centerPanel.add(textPanel, BorderLayout.NORTH);
        centerPanel.add(buttonPanel, BorderLayout.CENTER);


        add(title, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }
}
