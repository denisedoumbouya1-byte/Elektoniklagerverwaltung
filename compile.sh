#!/usr/bin/env bash
# Kompilierscript fuer die Praktikumsdokumentation
# Benoetigt: TeX Live oder MiKTeX mit pdflatex und biber
set -e
MAIN="main"

echo "==> Pass 1: pdflatex"
pdflatex -interaction=nonstopmode "$MAIN.tex"

echo "==> Biber (Literaturverzeichnis)"
biber "$MAIN"

echo "==> Pass 2: pdflatex"
pdflatex -interaction=nonstopmode "$MAIN.tex"

echo "==> Pass 3: pdflatex (Referenzen finalisieren)"
pdflatex -interaction=nonstopmode "$MAIN.tex"

echo ""
echo "Fertig: $MAIN.pdf wurde erstellt."
