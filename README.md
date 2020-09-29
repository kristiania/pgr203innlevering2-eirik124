![Java CI with Maven](https://github.com/kristiania/pgr203innlevering2-eirik124/workflows/Java%20CI%20with%20Maven/badge.svg)

# pgr203innlevering2-eirik124

#### Gruppemedlemmer:
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

Oppgaven er utviklet med test-drevet parprogrammering, som har foregått ved at alle tre på gruppen har skrevet tester og passet denne til nestemann, som da i sin tur har fått testen til å passere.

### Hvordan det bygges
Baseres på programmet IntelliJ fremgangsmåte kan variere fra program til program

Man bygger .jar-filen ved å velge View -> Tool Windows -> Maven. Da åpnes det et Maven-vindu under ```Lifecycle```. Man velger så ```Package``` og deretter kjører den pakking og bygging på et par sekunder.

### Hvordan det kjøres

Man kjører serveren med .jar-filen ved å kjøre ```java -jar target/http-server.jar``` eller det man renamer filen til etter build. 
Default port er 8080. Nettsiden kjøres default på localhost:8080 eller den porten som blir satt av bruker.



