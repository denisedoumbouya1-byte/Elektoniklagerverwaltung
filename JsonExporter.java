import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * Erzeugt den JSON-Export für das Lager im Arbeitsverzeichnis.
 * Diese Klasse isoliert die Exportlogik und erzeugt manuelles JSON ohne externe Bibliotheken.
 */
public class JsonExporter {
    public static void exportToFile(List<Article> articles, String filename) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("[\n");
            Iterator<Article> iterator = articles.iterator();
            while (iterator.hasNext()) {
                writer.write(formatArticle(iterator.next()));
                writer.write(iterator.hasNext() ? ",\n" : "\n");
            }
            writer.write("]\n");
        }
    }

    private static String formatArticle(Article article) {
        return String.format(Locale.US,
                "  {\n" +
                        "    \"id\": %d,\n" +
                        "    \"name\": \"%s\",\n" +
                        "    \"quantity\": %d,\n" +
                        "    \"minimum\": %d,\n" +
                        "    \"price\": %.2f\n" +
                        "  }",
                article.getId(),
                escapeJson(article.getName()),
                article.getQuantity(),
                article.getMinimum(),
                article.getPrice());
    }

    private static String escapeJson(String value) {
        return value.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
