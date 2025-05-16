package modul;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CardRepository {
    private static List<String> cardNumberList = new ArrayList<String>();
    private static List<String> pinList = new ArrayList<String>();
    private static List<Integer> balanceList= new ArrayList<Integer>();

    public static  void write(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("log.csv"));
            for(Integer i =0;i < pinList.size();i++) {
                writer.write(cardNumberList.get(i)+","+pinList.get(i)+","+balanceList.get(i));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void newWrite(String cislo,String pin){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("log.csv",true));
            writer.write(cislo+","+ pin+ ",0");
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void read(){
        File logFile = new File("log.csv");
        try {
            if (!logFile.exists()) {

                logFile.createNewFile();
                CardRepository.newWrite("1111111111111111","1111");
            }
            BufferedReader reader = new BufferedReader(new FileReader(logFile));
            String tmp;
            while ((tmp = reader.readLine()) != null) {
                String[] fields = tmp.split(",");
                cardNumberList.add(fields[0]);
                pinList.add(fields[1]);
                balanceList.add(Integer.parseInt(fields[2]));
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("Chyba při práci se souborem log.csv", e);
        }
    }

    public static String getCardNumber(int index){return cardNumberList.get(index);}
    public static Integer getCardIndex(String cardNumber){return cardNumberList.indexOf(cardNumber);}

    public static String getPin(int index) { return pinList.get(index); }
    public static void setPin(int index, String newPin) { pinList.set(index, newPin); }

    public static int getBalance(int index) { return balanceList.get(index); }
    public static void setBalance(int index, int newBalance) { balanceList.set(index, newBalance); }

}
