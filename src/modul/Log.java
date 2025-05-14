package modul;

import java.io.*;
import java.util.ArrayList;

public class Log {
    public static ArrayList<String> cardNumberArrayList = new ArrayList<String>();
    public static ArrayList<String> pinArrayList = new ArrayList<String>();
    public static ArrayList<Integer> zustatekArrayList= new ArrayList<Integer>();

    public  static  void write(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("log.csv"));
            for(Integer i =0;i < pinArrayList.size();i++) {
                writer.write(cardNumberArrayList.get(i)+","+pinArrayList.get(i)+","+zustatekArrayList.get(i));
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
                // Vytvoříme prázdný soubor
                logFile.createNewFile();
                Log.newWrite("1111111111111111","1111");// nic nečteme, seznamy zůstanou prázdné
            }
            // Pokud soubor existuje, načteme z něj data
            BufferedReader reader = new BufferedReader(new FileReader(logFile));
            String tmp;
            while ((tmp = reader.readLine()) != null) {
                String[] fields = tmp.split(",");
                cardNumberArrayList.add(fields[0]);
                pinArrayList.add(fields[1]);
                zustatekArrayList.add(Integer.parseInt(fields[2]));
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("Chyba při práci se souborem log.csv", e);
        }
    }
}
