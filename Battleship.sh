#!/bin/bash
cd IABattleship/
javac src/guary/nicolas/*.java src/fr/battleship/*.java src/fr/igpolytech/guary/exceptions/*.java
cd src
java guary.nicolas.Battleship 

find . -type f -path "./guary/nicolas/*" -name "*.class" -exec rm -f {} \;
find . -type f -path "./guary/nicolas/*/*" -name "*.class" -exec rm -f {} \;
find . -type f -path "./guary/nicolas/*/*/*" -name "*.class" -exec rm -f {} \;
find . -type f -path "./guary/nicolas/*/*/*/*" -name "*.class" -exec rm -f {} \;

find . -type f -path "./fr/battleship/*" -name "*.class" -exec rm -f {} \;
find . -type f -path "./fr/battleship/*/*" -name "*.class" -exec rm -f {} \;
find . -type f -path "./fr/battleship/*/*/*" -name "*.class" -exec rm -f {} \;
find . -type f -path "./fr/battleship/*/*/*/*" -name "*.class" -exec rm -f {} \;

find . -type f -path "./fr/igpolytech/guary/exceptions/*" -name "*.class" -exec rm -f {} \;
find . -type f -path "./fr/igpolytech/guary/exceptions/*/*" -name "*.class" -exec rm -f {} \;
find . -type f -path "./fr/igpolytech/guary/exceptions/*/*/*" -name "*.class" -exec rm -f {} \;
find . -type f -path "./fr/igpolytech/guary/exceptions/*/*/*/*" -name "*.class" -exec rm -f {} \;