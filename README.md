﻿
# AutoChess - Etapa 2

Echipa - Andrei Pantelimon & Temelie Petru
Grupa - 325CA
Github - [https://github.com/andreipantelimon/AutoChess](https://github.com/andreipantelimon/AutoChess) (Repository Privat)

Etapa1: Pentru aceasta etapa am implementat interfatarea cu programul Xboard precum si mutarea unei singure piese, a pionului. Main.Engine-ul isi genereaza automat mutari cat poate, iar cand nu mai poate muta niciun pion, acesta va da resign. De asemenea, cand acesta este in sah de la un pion va da resign, fara a incerca sa iasa (aceasta actiune nu merge daca este in sah de la alta piesa).

Etapa2: Pentru etapa 2 am implementat toate regulile sahului. Piesele genereaza mutarile corect si engine-ul recunoaste toate mutarile primite din xboard, inclusiv en passant, rocada si pawn promotion.


# Comenzi

In acest moment, in Main.Engine sunt implementate urmatoarele comenzi conform documentatiei Xboard de interfatare:

 - xboard, new, quit, protover, accepted, level, post, hard - pentru initializare
 - force, go, black, white, analyze - pentru testare folosind Analysis Mode
 - usermove, time, otim - pentru derularea jocului


Impartire task-uri:
- Petru Temelie - generarea, efectuarea mutarilor si verificarea daca este in sah engine-ul
- Andrei Pantelimon - Interfatarea cu xboard, initializarea board-ului

# Rularea Main.Engine-ului

Ca limbaj de programare am ales sa folosim Java, deoarece am gandit bot-ul mai Object-Oriented. 

 Pentru compilare (din folderul src al proiectului):
` javac Main.Main.java`

Pentru rulare (din folderul src al proiectului):
` java Main.Main`

 

<!--stackedit_data:
eyJoaXN0b3J5IjpbMTI0ODgyMzYxNF19
-->