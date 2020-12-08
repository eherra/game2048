## Arkkitehtuuri
### Rakenne
Ohjelmassa on neljä pakkausta, joista pakkaus **game2048.ui** sisältää javaFX:llä toteutetun käyttöliittymänäkymät ja **game2048.utils** siihen liittyvän apuluokan. 

Pakkaus **game2048.domain** sisältää ohjelman pelilogiikan sekä **game2048.dao** sisältää ohjelman pisteiden tallentamisen SQLite tietokantaan.

<img src="https://i.ibb.co/f2YcPDB/Screen-Shot-2020-12-08-at-11-54-04.png">

### Käyttöliittymä
Ohjelman käyttöliittymä sisältää näkymät:
* Aloitusnäky (mahdollisuudet pelin luomiseen, siirtymiseen High score-näkymään tai AI dogen valintaan)
* Pelinäkymä
* High score-näkymä

Näkymät ovat luotu Scene-olioilla, jotka haetaan käyttöliittymään tehdyillä metodeilla ja päivitetään Stage-näkymään. 

### Sovelluslogiikka
Alla pakkaus/luokkakaavio joka kuvaa sovelluksen pakkauksien ja luokkien suhteita:

<img src= "https://i.ibb.co/1ZGPVBC/Screen-Shot-2020-12-08-at-12-18-46.png">

### Toiminnallisuudet 
Kun aloitusnäkymästä löytyvästä "Quick start" napista painetaan, tapahtuu sovelluksessa seuraavaa:

<img src= "https://i.ibb.co/f2dc5DQ/Screen-Shot-2020-11-28-at-16-39-45.png">


### Tietokanta
Ohjelman pysyväistallennus on tehty SQLite tietokantaan käyttäen. Pakkauksen **game2048.dao**:sta löytyvä luokka DBHighScoreDao hoitaa SQL-kyselyt ja tallennukset tietokantaan, jota hallinnoidaan Database-luokan kanssa. Tietokantaan tallennetaan aina mikäli pelattu peli päättyy pisteisiin, jotka ovat arvoltaan vähintään top5:sen verran. Tietokannassa on sarakkeet _id_, _score_, _boardsize_ ja _date_.

High score-näkymä näyttää pelien 3x3, 4x4 ja 5x5 top 5-tulokset ja ne haetaan tietokannasta DBHighScoreDao:n avulla. 

Pelinäkymän luodessa tietokannasta haetaan valitseman pelilaudan koon mukainen high score ja asetetaan se pelinäkymän oikeaan yläkulmaan kohtaan "High Score". 
