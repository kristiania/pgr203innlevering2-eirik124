![Java CI with Maven](https://github.com/kristiania/pgr203innlevering2-eirik124/workflows/Java%20CI%20with%20Maven/badge.svg)

# pgr203innlevering2-eirik124

#### GRUPPEMEDLEMMER:
Pål Anders Byenstuen, Eirik Lundanes og Vibeke Opgård.

-----------------------------------------------------------

SJEKKLISTE ♥
----------
* [x] Koden er sjekket inn på github.com/kristiania-repository
* [x] Opprett repository med Github Classroom link: https://classroom.github.com/a/U8dpT5Q0
* [x] README.md inneholder en korrekt Github Actions badge
* [x] mvn package bygger en executable jar-fil
* [x] java -jar target/...jar (etter mvn package) starter opp en webserver
* [x] README.md beskriver prosjektet, hvordan man bygger det, hvordan man kjører serveren og hvilken URL den starter på
* [x] Webserveren kan levere statiske HTML- og CSS-filer
* [ ] Webserveren kan liste eksisterende prosjektmedlemmer med HTTP GET
* [x] Webserveren kan serve mer enn én http request før den må restartes
* [x] Koden inneholder et godt sett med tester og testene kjører i Github Actions. Test av serveren skal bruke deres egen http client klasse
* [x] Koden inneholder en HttpClient klasse med tester mot http://urlecho.appspot.com
* [x] GitHub repository er private
* [x] Veilederne er lagt til som Collaborators på GitHub repository
  * PanRagon, SeventhDisaster, omamoh18, iaffs
* [x] Dere har committed kode med alle prosjektdeltagernes GitHub konto
* [x] Alle prosjektdeltagerne har levert i på Canvas med link til Github repository

-----------------------------------------------------------

## Beskrivelse av prosjekt

Denne oppgaven er blitt utviklet med å parprogrammering og test-drevet utvikling. Her har vi satt opp en Http-server og skrevet tester. Vi har vært tre stykker på gruppen vår, og annenhver har failet og passert tester.

### Hvordan det bygges
Baseseres på programmet IntelliJ fremgangsmåte kan variere fra program til program

Man bygger .jar filen med å velge View -> Tool Windows -> Maven da åpner et maven vindu seg under ```Lifecycle``` velg ```Package```så kjører den pakking og byggingen på et par sekunder og se neste avsnitt for hvordan man kjører programmet 

### Hvordan det kjøres

Man kjører serveren med jar filen med å kjøre ```java -jar target/http-server.jar``` eller hva man renamer filnen med etter build. 
Default port er 8080. Nettsiden kjøres default på localhost:8080 eller hvilken port som blir satt av bruker



