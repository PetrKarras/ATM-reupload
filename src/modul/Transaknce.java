package modul;

public class Transaknce {

    public static void Vyber(Integer cardIndex,Integer vyber){
        Log.zustatekArrayList.set(cardIndex,Log.zustatekArrayList.get(cardIndex)-vyber);
        Log.write();
    }
    public static void Vklad(Integer cardIndex,Integer vklad){
        Log.zustatekArrayList.set(cardIndex, Log.zustatekArrayList.get(cardIndex)+vklad);
        Log.write();
    }
    public static String Vypis(Integer cardIndex){
        return "Váš zůstatek je "+Log.zustatekArrayList.get(cardIndex);
    }
    public static void ZmenaPin(Integer cardIndex,String novyPin){
        Log.pinArrayList.set(cardIndex,novyPin);
        Log.write();
    }
}
