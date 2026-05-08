# Elektroniklagerverwaltung

Ein konsolenbasiertes Lagerverwaltungssystem in Java, entwickelt im Rahmen des Praktikums  
**„Vom Wasserfall zum Sprint“** an der Hochschule Bielefeld (HSBI).

---

# Projektbeschreibung

Dieses Projekt simuliert ein kleines Elektroniklager und ermöglicht die Verwaltung von Artikeln über eine Konsolenanwendung.

Die Anwendung unterstützt:
- Artikelverwaltung
- Lagerbestandsverwaltung
- Mindestbestandswarnungen
- JSON-Export
- Fehlerbehandlung
- Konsolenmenü

Das Projekt wurde erstellt, um Unterschiede zwischen dem **Wasserfallmodell** und **Scrum / agiler Entwicklung** praktisch zu erleben.

---

# Funktionen

## Artikel hinzufügen
Neue Artikel können mit folgenden Informationen angelegt werden:
- Artikel-ID
- Name
- Menge
- Mindestbestand
- Preis

---

## Artikel anzeigen
Alle vorhandenen Artikel werden tabellarisch ausgegeben.

Beispiel:

| ID | Name | Quantity | Minimum | Price | Status |
|----|------|-----------|----------|--------|--------|
| 101 | Tastatur | 5 | 2 | 200.00 € | OK |

---

## Artikel löschen
Artikel können anhand ihrer ID entfernt werden.

---

## Bestand erhöhen
Der Lagerbestand eines Artikels kann erhöht werden.

---

## Bestand reduzieren
Der Lagerbestand kann reduziert werden.  
Das System verhindert negative Bestände.

---

## Mindestbestandswarnung
Wenn der Lagerbestand unter den Mindestbestand fällt, erscheint eine Warnung.

---

## JSON-Export
Alle Artikeldaten können als JSON-Datei exportiert werden.

Datei:
```json
warehouse_export.json
```

---

# Projektstruktur

```text
Elektroniklagerverwaltung/
│
├── Article.java
├── Warehouse.java
├── Main.java
├── InputHelper.java
├── JsonExporter.java
├── compile.sh
├── warehouse_export.json
├── main.tex
└── literatur.bib
```

---

# Klassenbeschreibung

## Article.java
Modellklasse für einen Artikel.

Enthält:
- ID
- Name
- Menge
- Mindestbestand
- Preis

---

## Warehouse.java
Geschäftslogik des Lagers.

Funktionen:
- Artikel hinzufügen
- Artikel löschen
- Bestand ändern
- Validierung
- Warnungen

---

## InputHelper.java
Hilfsklasse für Benutzereingaben über die Konsole.

---

## JsonExporter.java
Exportiert Artikeldaten als JSON-Datei.

---

## Main.java
Startpunkt der Anwendung und Konsolenmenü.

---

# Technologien

- Java
- Objektorientierte Programmierung (OOP)
- Git
- GitHub
- JSON

---

# Entwicklungsmodell

Dieses Projekt wurde im Rahmen des Praktikums  
**„Vom Wasserfall zum Sprint“** entwickelt.

Dabei wurden zwei Vorgehensmodelle verglichen.

---

## Phase 1 – Wasserfallmodell

Eigenschaften:
- Feste Anforderungen
- Klare Planung
- Keine Änderungen während der Entwicklung
- Fokus auf Dokumentation

### Vorteile
- Gute Struktur
- Hohe Planbarkeit
- Übersichtliche Entwicklung

### Nachteile
- Wenig Flexibilität
- Änderungen schwer integrierbar
- Gefahr am Kunden vorbei zu entwickeln

---

## Phase 2 – Scrum / Agile Entwicklung

Eigenschaften:
- Entwicklung in kurzen Sprints
- Kundenfeedback
- Flexible Anpassungen
- Iterative Verbesserung

### Vorteile
- Schnelles Feedback
- Flexible Entwicklung
- Verbesserte Zusammenarbeit

### Nachteile
- Mehr Kommunikation notwendig
- Änderungen können Planung beeinflussen

---

# Reflexion

Während des Projekts wurde deutlich, dass agile Methoden besonders hilfreich sind, wenn Anforderungen sich ändern oder Feedback berücksichtigt werden muss.

Das Wasserfallmodell bietet dagegen eine klare Struktur und eignet sich gut für stabile Anforderungen.

---

# Beispielablauf

## Artikel hinzufügen

```text
1) Add article

ID: 101
Name: Tastatur
Quantity: 5
Minimum: 2
Price: 200

[OK] Artikel erfolgreich hinzugefügt.
```

---

## Artikel anzeigen

```text
ID | Name      | Quantity | Minimum | Price | Status
101 | Tastatur | 5 | 2 | 200.00 | OK
```

---

## Bestand erhöhen

```text
ID: 101
Amount: 3000

[OK] Bestand erfolgreich erhöht.
```

---

## Fehlerbehandlung

```text
[ERROR] Artikel mit dieser ID wurde nicht gefunden.
```

---

# Installation

## Repository klonen

```bash
git clone https://github.com/DEIN_USERNAME/Elektroniklagerverwaltung.git
```

---

# Kompilieren

```bash
javac *.java
```

---

# Programm starten

```bash
java Main
```

---

# GitHub

Das Projekt wird mit Git und GitHub versioniert.

---

# Autor

**Denise Doumbouya**  
HSBI – Hochschule Bielefeld  
Praktikum Entwicklungsmethoden

