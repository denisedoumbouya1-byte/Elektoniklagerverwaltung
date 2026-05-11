
# Elektroniklagerverwaltung

Ein konsolenbasiertes Lagerverwaltungssystem in Java, entwickelt im Rahmen des Praktikums  
**„Vom Wasserfall zum Sprint“** an der Hochschule Bielefeld (HSBI).

Das Projekt simuliert ein kleines Elektroniklager und ermöglicht die Verwaltung verschiedener Artikel inklusive Lagerbestand, Mindestbestandswarnungen und JSON-Export.

---

# Projektbeschreibung

Die Anwendung dient zur Verwaltung eines Elektroniklagers über eine interaktive Konsolenoberfläche.

Besondere Erweiterung des Systems:

- Unterstützung verschiedener Maßeinheiten
- Verwaltung von Stückzahlen, Gewicht und Volumen
- Mindestbestandskontrolle
- Fehlerbehandlung bei ungültigen Eingaben
- JSON-Datenexport
- Strukturierte Konsolenausgabe
- Objektorientierte Architektur

Das Projekt wurde entwickelt, um Unterschiede zwischen klassischen und agilen Entwicklungsmodellen praktisch zu erleben.

---

# Funktionen

## Artikel hinzufügen

Neue Artikel können mit folgenden Informationen angelegt werden:

- Artikel-ID
- Name
- Menge
- Einheit
- Mindestbestand
- Preis

Unterstützte Einheiten:

| Einheit | Beschreibung |
|---|---|
| Stück | Einzelstücke |
| kg | Kilogramm |
| t | Tonnen |
| mL | Milliliter |
| L | Liter |
| m³ | Kubikmeter |

---

## Artikel anzeigen

Alle Artikel werden tabellarisch dargestellt.

Beispiel:

```text
==============================================================
ID   | Name                   | Quantity   | Unit | Minimum  | Price      | Status
==============================================================
101  | Maus                   |       3,00 | Stück |        1 |     300,00 | OK
202  | Lötzinn                |       0,50 | kg   |       11 |      35,00 | ! WARNING !
==============================================================
```

---

## Artikel löschen

Artikel können anhand ihrer ID entfernt werden.

---

## Bestand erhöhen

Der Lagerbestand eines Artikels kann erhöht werden.

---

## Bestand verringern

Der Lagerbestand kann reduziert werden.

Das System verhindert:

- negative Bestände
- ungültige Eingaben
- nicht existierende Artikel

---

## Mindestbestandswarnung

Wenn ein Artikel unter den Mindestbestand fällt, erscheint automatisch eine Warnung.

Beispiel:

```text
[WARN] Es gibt Artikel unter dem Mindestbestand.
```

---

## JSON-Export

Alle Artikeldaten können als JSON-Datei exportiert werden.

Exportdatei:

```text
warehouse_export.json
```

---

# Beispielablauf

## Programmstart

```text
==============================================================
  Elektroniklagerverwaltungssystem
==============================================================
Willkommen! Bitte wählen Sie einen der folgenden Menüpunkte aus.
==============================================================
```

---

## Artikel hinzufügen

```text
Ihre Wahl: 1

==============================================================
-- Artikel anlegen --
==============================================================
ID: 101
Name: Maus
Quantity: 3

==============================================================
-- Einheit wählen --
==============================================================
  1) Stück
  2) kg
  3) t
  4) mL
  5) L
  6) m³
==============================================================

Einheit auswählen [1-6]: 1
Minimum: 1
Price: 300

[INFO] Artikel 'Maus' erfolgreich hinzugefügt.
[OK] Artikel erfolgreich hinzugefügt.
```

---

## Artikelübersicht

```text
==============================================================
ID   | Name                   | Quantity   | Unit | Minimum  | Price      | Status
==============================================================
101  | Maus                   |       3,00 | Stück |        1 |     300,00 | OK
202  | Lötzinn                |       0,50 | kg   |       11 |      35,00 | ! WARNING !
==============================================================
```

---

## Fehlerbehandlung

Ungültige Menüauswahl:

```text
[ERROR] Ungültige Auswahl. Bitte wählen Sie eine Option zwischen 0 und 6.
```

Ungültige Eingabe:

```text
Ungültige Eingabe. Bitte eine ganze Zahl eingeben.
```

Nicht vorhandener Artikel:

```text
[ERROR] Artikel mit dieser ID wurde nicht gefunden.
```

---

# Projektstruktur

```text
Elektroniklagerverwaltung/
│
├── Article.java
├── Unit.java
├── Warehouse.java
├── Main.java
├── InputHelper.java
├── JsonExporter.java
├── warehouse_export.json
├── compile.sh
├── main.tex
├── literatur.bib
└── README.md
```

---

# Klassenbeschreibung

## Article.java

Modellklasse für einen Artikel.

Enthält:

- ID
- Name
- Menge
- Einheit
- Mindestbestand
- Preis

---

## Unit.java

Enum-Klasse für unterstützte Maßeinheiten.

Unterstützte Einheiten:

- Stück
- kg
- t
- mL
- L
- m³

---

## Warehouse.java

Geschäftslogik des Lagers.

Funktionen:

- Artikel hinzufügen
- Artikel löschen
- Bestand ändern
- Validierung
- Mindestbestandsprüfung
- Warnungen

---

## InputHelper.java

Hilfsklasse zur sicheren Verarbeitung von Benutzereingaben.

Enthält:

- Integer-Eingaben
- Double-Eingaben
- Fehlerbehandlung
- Menüvalidierung

---

## JsonExporter.java

Exportiert Artikeldaten als JSON-Datei.

---

## Main.java

Startpunkt der Anwendung.

Enthält:

- Hauptmenü
- Konsolensteuerung
- Benutzerinteraktion

---

# Technologien

Dieses Projekt verwendet:

- Java
- Objektorientierte Programmierung (OOP)
- Git
- GitHub
- JSON
- Konsolenanwendung

---

# Entwicklungsmodell

Das Projekt wurde im Rahmen des Praktikums  
**„Vom Wasserfall zum Sprint“** entwickelt.

Dabei wurden zwei Vorgehensmodelle verglichen.

---

# Phase 1 – Wasserfallmodell

## Eigenschaften

- Feste Anforderungen
- Klare Planung
- Fokus auf Dokumentation
- Wenige Änderungen während der Entwicklung

## Vorteile

- Gute Struktur
- Hohe Planbarkeit
- Übersichtliche Entwicklung

## Nachteile

- Wenig Flexibilität
- Änderungen schwer integrierbar
- Risiko an Anforderungen vorbei zu entwickeln

---

# Phase 2 – Scrum / Agile Entwicklung

## Eigenschaften

- Entwicklung in kurzen Sprints
- Kundenfeedback
- Iterative Verbesserung
- Flexible Anpassungen

## Vorteile

- Schnelles Feedback
- Flexible Entwicklung
- Verbesserte Zusammenarbeit

## Nachteile

- Höherer Kommunikationsaufwand
- Änderungen beeinflussen Planung

---

# Erweiterungen im Sprint

Im späteren Sprint wurden neue Anforderungen ergänzt:

- Unterstützung von Maßeinheiten
- Verwaltung von Volumen und Masse
- Erweiterte Validierung
- Verbesserte Konsolenausgabe
- Erweiterte Fehlerbehandlung

Diese Änderungen zeigen die Vorteile agiler Entwicklung bei wechselnden Anforderungen.

---

# Installation

## Repository klonen

```bash
git clone https://github.com/DEIN_USERNAME/Elektroniklagerverwaltung.git
```

---

## In Projektordner wechseln

```bash
cd Elektroniklagerverwaltung
```

---

## Kompilieren

```bash
javac *.java
```

---

## Programm starten

```bash
java Main
```

---

# Reflexion

Während des Projekts wurde deutlich, dass agile Methoden besonders hilfreich sind, wenn Anforderungen sich ändern oder neues Feedback berücksichtigt werden muss.

Die Erweiterung um verschiedene Maßeinheiten konnte flexibel integriert werden und zeigt die Vorteile iterativer Entwicklung.

Das Wasserfallmodell bietet dagegen klare Planung und Struktur, eignet sich jedoch eher für stabile Anforderungen.

---

# Verbesserungsmöglichkeiten

Mögliche zukünftige Erweiterungen:

- Datenbankanbindung
- Benutzerverwaltung
- GUI statt Konsolenanwendung
- CSV-Export
- Suchfunktion
- Sortierung
- Persistente Speicherung
- Automatische Tests
- REST-API

---

# Autorin

**Denise Doumbouya**  
HSBI – Hochschule Bielefeld  
Praktikum Entwicklungsmethoden
