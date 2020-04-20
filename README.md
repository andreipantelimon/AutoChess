
# AutoChess - Etapa 2

Echipa - Andrei Pantelimon & Temelie Petru
Grupa - 325CA
Github - [https://github.com/andreipantelimon/AutoChess](https://github.com/andreipantelimon/AutoChess) (Repository Privat)
## Etapa 1
Pentru aceasta etapa am implementat interfatarea cu programul Xboard precum si mutarea unei singure piese, a pionului. Main.Engine-ul isi genereaza automat mutari cat poate, iar cand nu mai poate muta niciun pion, acesta va da resign. De asemenea, cand acesta este in sah de la un pion va da resign, fara a incerca sa iasa (aceasta actiune nu merge daca este in sah de la alta piesa).

## Etapa 2
Pentru etapa 2 am implementat toate regulile sahului. Piesele genereaza mutarile corect si engine-ul recunoaste toate mutarile primite din xboard, inclusiv en passant, rocada si pawn promotion.
Am implementat pentru logica de generare a celei mai bune mutari un negamax, cu o functie de evaluare ce calculeaza o miscare buna bazata pe numarul de piese curente din board (spre exemplu o mutare buna ar fi daca ar manca o piesa), dar si pe pozitia unei piese din board (pionii sunt "fortati" sa inainteze, iar pentru rege este bine sa nu se miste), coeficienti ce sunt prezenti in array-uri.

# Comenzi

In acest moment, in Main.Engine sunt implementate urmatoarele comenzi conform documentatiei Xboard de interfatare:

 - xboard, new, quit, protover, accepted, level, post, hard - pentru initializare
 - force, go, black, white, analyze - pentru testare folosind Analysis Mode
 - usermove, time, otim - pentru derularea jocului


Impartire task-uri:
### Etapa 1
- Petru Temelie - generarea, efectuarea mutarilor si verificarea daca este in sah engine-ul
- Andrei Pantelimon - Interfatarea cu xboard, initializarea board-ului
### Etapa 2
- Petru Temelie - generarea tuturor miscarilor legale, precum si verificarea daca regele este in sah si cum poate acesta sa iasa
- Andrei Pantelimon - implementarea mini

# Rularea Engine-ului

Ca limbaj de programare am ales sa folosim Java, deoarece am gandit bot-ul mai Object-Oriented. 

 Pentru compilare (din folderul src al proiectului):
` make build`

Pentru rulare (din folderul src al proiectului):
` make run`

Pentru clean:
` make clean`

 

<!--stackedit_data:
eyJoaXN0b3J5IjpbLTU1ODU0MDYyXX0=
-->