package modul;

import javax.swing.*;
import java.awt.*;

public class UIService {
    private  static Integer pocetPokusu =3;

    public static void PinConfirm(String pin,JPanel panel,CardLayout cardLayout){
        if (pocetPokusu == 0) {
            JOptionPane.showMessageDialog(panel, "Příliš mnoho pokusů. Zpět na úvodní obrazovku.", "Chyba", JOptionPane.ERROR_MESSAGE);
            Back(panel,cardLayout,"WELCOME");
            pocetPokusu=3;
            return;
        }

        if (pin.isEmpty()) {
            JOptionPane.showMessageDialog(panel, "Textové pole je prázdné. Prosím zadejte PIN.", "Chyba", JOptionPane.ERROR_MESSAGE);
        } else if (pin.length() != 4) {
            JOptionPane.showMessageDialog(panel, "PIN má 4 číslice. Prosím zadejte ho znovu", "Chyba", JOptionPane.ERROR_MESSAGE);
        } else if (CardService.getSignCardIndex() == -1) {
            CardRepository.newWrite(CardService.getSignCardNumber(), pin);
            JOptionPane.showMessageDialog(panel, "Nová karta registrována", "Registrace", JOptionPane.INFORMATION_MESSAGE);
            pocetPokusu=3;
            cardLayout.show(panel, "MENU");
        } else if (!pin.equals(CardRepository.getPin(CardService.getSignCardIndex()))) {
            JOptionPane.showMessageDialog(panel, "Nesprávný PIN", "Chyba", JOptionPane.ERROR_MESSAGE);
            pocetPokusu--;
        } else {
            pocetPokusu=3;
            cardLayout.show(panel, "MENU");
        }

        System.out.println("Zbývající pokusy: " + pocetPokusu);
    }
    public static void Login(String text, JPanel panel, CardLayout layout) {
        
        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(panel, 	"Prosím, číslo karty.", "Chyba", JOptionPane.ERROR_MESSAGE);
        } else if (text.length() != 16){
            JOptionPane.showMessageDialog(panel, "Číslo karty má 16 číslic. Prosím zadejte ho znovu", "Chyba", JOptionPane.ERROR_MESSAGE);
        } else {
            CardService.setSignCardNumber(text);
            UIService.clearTextFields(panel);
            layout.show(panel, "PIN");
        }
    }

    public static void Back(JPanel panel, CardLayout cardLayout,String screenName) {
        cardLayout.show(panel, screenName);
    }
    public static void clearTextFields(JPanel panel) {
        for (Component component : panel.getComponents()) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText("");
            } else if (component instanceof JPasswordField) {
                ((JPasswordField) component).setText("");
            } else if (component instanceof JPanel) {
                // Pro vnořené panely (rekurzivně)
                clearTextFields((JPanel) component);
            }
        }
    }

}
