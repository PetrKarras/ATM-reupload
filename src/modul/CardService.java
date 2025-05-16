package modul;

public class CardService {
    private static String signCardNumber = null;
    private static Integer signCardIndex = null;
    
    public static Result Vyber(String vyberString){
        System.out.println(vyberString);

        if(vyberString.isEmpty())
        {
            return Result.TOO_MUCH;
        }
        int vyberInt;
        try {
            vyberInt = Integer.parseInt(vyberString);
        }
        catch (NumberFormatException e)
        {
            return Result.INVALID;
        }
        if (vyberInt <= CardRepository.getBalance(signCardIndex)){
            int balance = CardRepository.getBalance(signCardIndex);
            CardRepository.setBalance(signCardIndex,balance-vyberInt );
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
        int vkladInt;
        try {
            vkladInt = Integer.parseInt(vkladString);
        }
        catch (NumberFormatException e)
        {
            return Result.TOO_MUCH;
        }
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
    public static void  setSignCardIndex(String SignCardNumber){signCardIndex = CardRepository.getCardIndex(SignCardNumber);
        System.out.println("Karta: "+signCardNumber+"Index: "+signCardIndex);}

}
