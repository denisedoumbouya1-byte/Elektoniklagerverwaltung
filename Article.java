/**
 * Repräsentiert einen Artikel im Elektroniklager.
 * Diese Klasse kapselt Validierungen für Artikelattribute, Mengeneinheiten und Bestandsänderungen.
 */
public class Article {
    private final int id;
    private String name;
    private double quantity;
    private int minimum;
    private Unit unit;
    private double price;

    public Article(int id, String name, double quantity, Unit unit, int minimum, double price) {
        validateId(id);
        validateName(name);
        validateQuantity(quantity);
        validateUnit(unit);
        validateMinimum(minimum);
        validatePrice(price);

        this.id = id;
        this.name = name.trim();
        this.quantity = quantity;
        this.unit = unit;
        this.minimum = minimum;
        this.price = price;
    }

    public Article(int id, String name, double quantity, int minimum, double price) {
        this(id, name, quantity, Unit.PIECE, minimum, price);
    }

    private static void validateId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("ID muss eine nicht negative ganze Zahl sein.");
        }
    }

    private static void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name darf nicht leer sein.");
        }
    }

    private static void validateQuantity(double quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity darf nicht negativ sein.");
        }
    }

    private static void validateUnit(Unit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit darf nicht null sein.");
        }
    }

    private static void validateMinimum(int minimum) {
        if (minimum < 0) {
            throw new IllegalArgumentException("Minimum darf nicht negativ sein.");
        }
    }

    private static void validatePrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price darf nicht negativ sein.");
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }

    public Unit getUnit() {
        return unit;
    }

    public int getMinimum() {
        return minimum;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name.trim();
    }

    public void setQuantity(double quantity) {
        validateQuantity(quantity);
        this.quantity = quantity;
    }

    public void setUnit(Unit unit) {
        validateUnit(unit);
        this.unit = unit;
    }

    public void setMinimum(int minimum) {
        validateMinimum(minimum);
        this.minimum = minimum;
    }

    public void setPrice(double price) {
        validatePrice(price);
        this.price = price;
    }

    public boolean increaseQuantity(double amount) {
        if (amount <= 0) {
            return false;
        }
        quantity += amount;
        return true;
    }

    public boolean decreaseQuantity(double amount) {
        if (amount <= 0 || amount > quantity) {
            return false;
        }
        quantity -= amount;
        return true;
    }

    public boolean isBelowMinimum() {
        return quantity < minimum;
    }

    @Override
    public String toString() {
        return String.format(
                "ID: %d | Name: %s | Quantity: %.2f %s | Minimum: %d | Price: %.2f",
                id, name, quantity, unit, minimum, price);
    }
}
