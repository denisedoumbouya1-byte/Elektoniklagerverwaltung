/**
 * Einheit für Artikelmengen im Lagerverwaltungssystem.
 * Unterstützt Stück, Masse- und Volumeneinheiten.
 */
public enum Unit {
    PIECE("Stück"),
    KG("kg"),
    T("t"),
    ML("mL"),
    L("L"),
    M3("m³");

    private final String symbol;

    Unit(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
