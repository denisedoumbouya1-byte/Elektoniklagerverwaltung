import java.util.Scanner;

/**
 * Unterstützt fehlerresistente Konsoleneingaben.
 * Diese Hilfsklasse trennt Eingabevalidierung vom Anwendungsfluss.
 */
public class InputHelper {
    public static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Ungültige Eingabe. Bitte eine ganze Zahl eingeben.");
            }
        }
    }

    public static int readNonNegativeInt(Scanner scanner, String prompt) {
        while (true) {
            int value = readInt(scanner, prompt);
            if (value >= 0) {
                return value;
            }
            System.out.println("Bitte eine nicht negative Zahl eingeben.");
        }
    }

    public static double readNonNegativeDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value >= 0.0) {
                    return value;
                }
                System.out.println("Bitte eine nicht negative Zahl eingeben.");
            } catch (NumberFormatException e) {
                System.out.println("Ungültige Eingabe. Bitte eine Gleitkommazahl eingeben.");
            }
        }
    }

    public static String readNonEmptyString(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Der Text darf nicht leer sein.");
        }
    }
}
