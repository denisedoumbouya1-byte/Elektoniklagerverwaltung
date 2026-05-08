import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Startklasse für das Elektroniklagerverwaltungssystem.
 * Diese Klasse steuert das Menü und präsentiert alle Ausgaben professionell.
 */
public class Main {
    private static final String JSON_FILENAME = "warehouse_export.json";
    private static final Warehouse warehouse = new Warehouse();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            run(scanner);
        }
    }

    private static void run(Scanner scanner) {
        boolean running = true;

        printWelcomeBanner();
        while (running) {
            printMenu();
            int choice = InputHelper.readNonNegativeInt(scanner, "Ihre Wahl: ");
            System.out.println();

            switch (choice) {
                case 1 -> addArticle(scanner);
                case 2 -> showArticles();
                case 3 -> deleteArticle(scanner);
                case 4 -> changeQuantity(scanner, true);
                case 5 -> changeQuantity(scanner, false);
                case 6 -> exportJson();
                case 0 -> {
                    printInfo("Beendet. Vielen Dank für die Nutzung des Elektroniklagersystems.");
                    running = false;
                }
                default -> printError("Ungültige Auswahl. Bitte wählen Sie eine Option zwischen 0 und 6.");
            }
            System.out.println();
        }
    }

    private static void printWelcomeBanner() {
        printSeparator();
        System.out.println("  Elektroniklagerverwaltungssystem");
        printSeparator();
        System.out.println("Willkommen! Bitte wählen Sie einen der folgenden Menüpunkte aus.");
        printSeparator();
        System.out.println();
    }

    private static void printMenu() {
        printSectionTitle("Hauptmenü");
        System.out.println("  1) Add article");
        System.out.println("  2) Show articles");
        System.out.println("  3) Delete article");
        System.out.println("  4) Increase quantity");
        System.out.println("  5) Decrease quantity");
        System.out.println("  6) Export JSON");
        System.out.println("  0) Exit");
        printSeparator();
    }

    private static void addArticle(Scanner scanner) {
        printSectionTitle("Artikel anlegen");
        int id = InputHelper.readNonNegativeInt(scanner, "ID: ");
        if (warehouse.articleExists(id)) {
            printError("Ein Artikel mit dieser ID existiert bereits.");
            return;
        }

        String name = InputHelper.readNonEmptyString(scanner, "Name: ");
        int quantity = InputHelper.readNonNegativeInt(scanner, "Quantity: ");
        int minimum = InputHelper.readNonNegativeInt(scanner, "Minimum: ");
        double price = InputHelper.readNonNegativeDouble(scanner, "Price: ");

        try {
            Article article = new Article(id, name, quantity, minimum, price);
            boolean added = warehouse.addArticle(article);
            if (added) {
                printSuccess("Artikel erfolgreich hinzugefügt.");
                printInfo("Aktueller Lagerbestand: " + warehouse.getArticles().size() + " Artikel.");
            } else {
                printError("Artikel konnte nicht hinzugefügt werden.");
            }
        } catch (IllegalArgumentException e) {
            printError("Fehler beim Anlegen des Artikels: " + e.getMessage());
        }
    }

    private static void showArticles() {
        printSectionTitle("Artikelübersicht");
        List<Article> articles = warehouse.getArticles();
        if (articles.isEmpty()) {
            printInfo("Derzeit sind keine Artikel im Lager vorhanden.");
            return;
        }

        printArticleTableHeader();
        for (Article article : articles) {
            printArticleRow(article);
        }
        printSeparator();
        printInfo("Anzahl Artikel: " + articles.size());
        if (warehouse.getLowStockArticles().size() > 0) {
            printWarning("Es gibt Artikel unter dem Mindestbestand.");
        }
    }

    private static void deleteArticle(Scanner scanner) {
        printSectionTitle("Artikel löschen");
        int id = InputHelper.readNonNegativeInt(scanner, "ID: ");
        boolean deleted = warehouse.deleteArticle(id);
        if (deleted) {
            printSuccess("Artikel erfolgreich gelöscht.");
        } else {
            printError("Artikel mit dieser ID wurde nicht gefunden.");
        }
    }

    private static void changeQuantity(Scanner scanner, boolean increase) {
        printSectionTitle(increase ? "Bestand erhöhen" : "Bestand verringern");
        int id = InputHelper.readNonNegativeInt(scanner, "ID: ");
        int amount = InputHelper.readNonNegativeInt(scanner, "Amount: ");

        boolean result = increase ? warehouse.increaseQuantity(id, amount) : warehouse.decreaseQuantity(id, amount);
        if (!result) {
            if (!warehouse.articleExists(id)) {
                printError("Artikel mit dieser ID wurde nicht gefunden.");
            } else if (!increase) {
                printError("Der Bestand darf nicht negativ werden.");
            } else {
                printError("Die Menge konnte nicht geändert werden.");
            }
            return;
        }

        printSuccess(increase ? "Bestand erfolgreich erhöht." : "Bestand erfolgreich verringert.");
    }

    private static void exportJson() {
        printSectionTitle("JSON Export");
        if (!warehouse.hasArticles()) {
            printInfo("Keine Artikel vorhanden, Export nicht möglich.");
            return;
        }

        try {
            JsonExporter.exportToFile(warehouse.getArticles(), JSON_FILENAME);
            printSuccess("Export erfolgreich: " + JSON_FILENAME);
        } catch (IOException e) {
            printError("Export fehlgeschlagen: " + e.getMessage());
        }
    }

    private static void printArticleTableHeader() {
        printSeparator();
        System.out.printf("%-4s | %-22s | %-8s | %-8s | %-10s | %s%n", "ID", "Name", "Quantity", "Minimum", "Price", "Status");
        printSeparator();
    }

    private static void printArticleRow(Article article) {
        String status = article.isBelowMinimum() ? "! WARNING !" : "OK";
        System.out.printf("%-4d | %-22s | %8d | %8d | %10.2f | %s%n",
                article.getId(), article.getName(), article.getQuantity(), article.getMinimum(), article.getPrice(), status);
    }

    private static void printSeparator() {
        System.out.println("==============================================================");
    }

    private static void printSectionTitle(String title) {
        printSeparator();
        System.out.println("-- " + title + " --");
        printSeparator();
    }

    private static void printSuccess(String message) {
        System.out.println("[OK] " + message);
    }

    private static void printInfo(String message) {
        System.out.println("[INFO] " + message);
    }

    private static void printWarning(String message) {
        System.out.println("[WARN] " + message);
    }

    private static void printError(String message) {
        System.out.println("[ERROR] " + message);
    }
}
