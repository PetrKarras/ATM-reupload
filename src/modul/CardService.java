package modul;

public class CardService {
    private static String signCardNumber = null;
    private static Integer signCardIndex = null;
    
    public static Result Vyber(String vyberText){
        System.out.println(vyberText);

        if(vyberText.isEmpty())
        {
            return Result.INVALID;
        }
        int vyber =Integer.parseInt( vyberText);
        if (vyber <= CardRepository.getBalance(signCardIndex)){
            int balance = CardRepository.getBalance(signCardIndex);
            CardRepository.setBalance(signCardIndex,balance-vyber );
            CardRepository.write();
            return Result.SUCCESS;

        } else {
            return Result.INSUFFICIENT;
        }
        

    }
    public static Result Vklad(String vkladString){
        if(vkladString.isEmpty())
        {
           // JOptionPane.showMessageDialog(panel, "Neplatná častka.", "Chyba", JOptionPane.ERROR_MESSAGE);
            return Result.INVALID;
        }
        int vkladInt = Integer.parseInt(vkladString);
        System.out.println(""+vkladInt);
        if (vkladInt > 0 ) {
            System.out.println(""+vkladInt);
            int balance = CardRepository.getBalance(signCardIndex);
            CardRepository.setBalance(signCardIndex,balance+vkladInt );
            CardRepository.write();
            return Result.SUCCESS;
            //JOptionPane.showMessageDialog(panel, "Vklad byl uspěšný.", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            return Result.INSUFFICIENT;
           // JOptionPane.showMessageDialog(panel, "Prosím, zadejte částku.", "Chyba", JOptionPane.ERROR_MESSAGE);
        }

    }
    public static Result PinChange(String newPin){
        if (newPin.length() <4) {
           return  Result.INVALID;
        } else {
            CardRepository.setPin(signCardIndex,newPin );
            CardRepository.write();
            return Result.SUCCESS;
        }
    }
    public static String Vypis(){
        if (signCardIndex != null && signCardIndex >= 0) {
            return "Váš zůstatek je "+ CardRepository.getBalance(signCardIndex);
        } else {
            return  "Chyba: Karta není načtena.";
        }

    }

    public static String getSignCardNumber(){return signCardNumber;}
    public static void setSignCardNumber(String newSignCardNumber){
        signCardNumber = newSignCardNumber;
        signCardIndex = CardRepository.getCardIndex(newSignCardNumber);
        System.out.println("Karta: "+signCardNumber+"Index: "+signCardIndex);
    }

    public static Integer getSignCardIndex(){return signCardIndex;}

}
