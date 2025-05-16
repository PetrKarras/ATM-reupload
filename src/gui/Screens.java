package gui;

import javax.swing.*;
import java.awt.*;

public class Screens extends JFrame {

    private final JPanel mainPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    public Screens() {
        super("ATM");
        initUI();
        loadScreens();
        showWelcome();
    }

    private void initUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        mainPanel.setLayout(cardLayout);
        add(mainPanel);
        setVisible(true);
    }

    private void loadScreens() {
        mainPanel.add(new WelcomeScreen(cardLayout, mainPanel), "WELCOME");
        mainPanel.add(new PinScreen(cardLayout, mainPanel), "PIN");
        mainPanel.add(new MenuScreen(cardLayout, mainPanel), "MENU");
        mainPanel.add(new VkladScreen(cardLayout, mainPanel), "VKLAD");
        mainPanel.add(new VyberScreen(cardLayout, mainPanel), "VYBER");
        mainPanel.add(new PinChangeScreen(cardLayout, mainPanel), "ZMENAPINU");
        mainPanel.add(new ZustatekScreen(cardLayout, mainPanel), "ZUSTATEK");
    }

    private void showWelcome() {
        cardLayout.show(mainPanel, "WELCOME");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Screens::new);
    }
}
