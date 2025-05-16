# ATM System - Java

Tento projekt simuluje funkce bankomatu v jazyce Java. Implementuje základní operace jako vklad, výběr, změnu PINu a zobrazení zůstatku karty.

## Funkce

- **Vklad:** Umožňuje uživateli vložit peníze na kartu.
- **Výběr:** Umožňuje uživateli vybrat peníze z karty.
- **Zobrazení zůstatku:** Umožňuje zobrazit aktuální zůstatek na kartě.
- **Změna PINu:** Umožňuje uživateli změnit PIN karty.
- **Trvalé ukládání:** Všechny operace jsou ukládány do CSV souboru, který slouží jako log pro karty, PINy a zůstatky.

## Složení projektu

1. **`modul`** - Hlavní logika aplikace.
   - `Log.java`: Třída pro správu logu (zápis, čtení) do souboru CSV.
   - `Transaknce.java`: Třída pro provádění bankovních operací (vklad, výběr, změna PINu).
   - `Main.java`: Hlavní třída pro spuštění aplikace.
   
2. **`gui`** - Grafické uživatelské rozhraní.
   - `ZmenaPinuScreen.java`: Panel pro změnu PINu.
   - `ZustatekScreen.java`: Panel pro zobrazení zůstatku karty.

## Jak spustit

1. Ujistěte se, že máte nainstalovanou Javu (JDK 8 nebo novější).
2. Klonujte tento repozitář do svého místního počítače.
3. V terminálu přejděte do složky projektu a spusťte aplikaci:
   ```bash
   javac -d bin src/**/*.java
   java -cp bin modul.Main
