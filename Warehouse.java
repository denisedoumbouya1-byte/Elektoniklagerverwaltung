import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Geschäftslogik für das Elektroniklager.
 * Verwaltet Artikel mit eindeutigen IDs, Bestandspflege und Abfragen.
 * Stellt sicher, dass keine negativen Bestände entstehen und warnt bei Mindestbestand.
 */
public class Warehouse {
    private final Map<Integer, Article> articles = new LinkedHashMap<>();

    /**
     * Fügt einen neuen Artikel hinzu, falls die ID noch nicht existiert.
     * @param article Der hinzuzufügende Artikel.
     * @return true, wenn erfolgreich hinzugefügt, false bei Duplikat-ID.
     * @throws IllegalArgumentException wenn article null ist.
     */
    public boolean addArticle(Article article) {
        if (article == null) {
            throw new IllegalArgumentException("Artikel darf nicht null sein.");
        }
        if (articles.containsKey(article.getId())) {
            System.out.println("[ERROR] Ein Artikel mit ID " + article.getId() + " existiert bereits.");
            return false;
        }
        articles.put(article.getId(), article);
        System.out.println("[INFO] Artikel '" + article.getName() + "' erfolgreich hinzugefügt.");
        return true;
    }

    /**
     * Entfernt einen Artikel anhand seiner ID.
     * @param id Die ID des zu löschenden Artikels.
     * @return true, wenn erfolgreich gelöscht, false wenn nicht gefunden.
     */
    public boolean deleteArticle(int id) {
        Article removed = articles.remove(id);
        if (removed != null) {
            System.out.println("[INFO] Artikel '" + removed.getName() + "' erfolgreich gelöscht.");
            return true;
        } else {
            System.out.println("[ERROR] Artikel mit ID " + id + " wurde nicht gefunden.");
            return false;
        }
    }

    /**
     * Erhöht die Menge eines Artikels.
     * @param id Die ID des Artikels.
     * @param amount Die zu erhöhende Menge (muss positiv sein).
     * @return true, wenn erfolgreich, false bei Fehler.
     */
    public boolean increaseQuantity(int id, int amount) {
        Article article = articles.get(id);
        if (article == null) {
            System.out.println("[ERROR] Artikel mit ID " + id + " wurde nicht gefunden.");
            return false;
        }
        if (amount <= 0) {
            System.out.println("[ERROR] Menge muss positiv sein.");
            return false;
        }
        article.increaseQuantity(amount);
        System.out.println("[INFO] Menge von '" + article.getName() + "' um " + amount + " erhöht.");
        return true;
    }

    /**
     * Verringert die Menge eines Artikels und warnt bei Mindestbestand.
     * @param id Die ID des Artikels.
     * @param amount Die zu verringernde Menge.
     * @return true, wenn erfolgreich, false bei Fehler.
     */
    public boolean decreaseQuantity(int id, int amount) {
        Article article = articles.get(id);
        if (article == null) {
            System.out.println("[ERROR] Artikel mit ID " + id + " wurde nicht gefunden.");
            return false;
        }
        if (amount <= 0) {
            System.out.println("[ERROR] Menge muss positiv sein.");
            return false;
        }
        if (amount > article.getQuantity()) {
            System.out.println("[ERROR] Nicht genügend Bestand vorhanden.");
            return false;
        }
        article.decreaseQuantity(amount);
        System.out.println("[INFO] Menge von '" + article.getName() + "' um " + amount + " verringert.");
        checkAndWarnLowStock(article);
        return true;
    }

    /**
     * Sucht einen Artikel anhand seiner ID.
     * @param id Die ID des Artikels.
     * @return Optional mit dem Artikel oder leer.
     */
    public Optional<Article> findById(int id) {
        return Optional.ofNullable(articles.get(id));
    }

    /**
     * Prüft, ob ein Artikel mit der gegebenen ID existiert.
     * @param id Die ID.
     * @return true, wenn existiert.
     */
    public boolean articleExists(int id) {
        return articles.containsKey(id);
    }

    /**
     * Gibt eine unveränderliche Liste aller Artikel zurück.
     * @return Liste der Artikel.
     */
    public List<Article> getArticles() {
        return Collections.unmodifiableList(new ArrayList<>(articles.values()));
    }

    /**
     * Prüft, ob Artikel vorhanden sind.
     * @return true, wenn mindestens ein Artikel vorhanden.
     */
    public boolean hasArticles() {
        return !articles.isEmpty();
    }

    /**
     * Gibt eine Liste von Artikeln zurück, die unter dem Mindestbestand liegen.
     * @return Liste der Artikel mit niedrigem Bestand.
     */
    public List<Article> getLowStockArticles() {
        List<Article> lowStock = new ArrayList<>();
        for (Article article : articles.values()) {
            if (article.isBelowMinimum()) {
                lowStock.add(article);
            }
        }
        return Collections.unmodifiableList(lowStock);
    }

    /**
     * Hilfsmethode: Prüft und warnt bei Mindestbestand.
     * @param article Der zu prüfende Artikel.
     */
    private void checkAndWarnLowStock(Article article) {
        if (article.getQuantity() < article.getMinimum()) {
            System.out.println("[WARNUNG] Mindestbestand unterschritten!");
        }
    }
}
