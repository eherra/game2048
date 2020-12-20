## Vaatimusmäärittely
### Sovelluksen tarkoitus
Sovellus on 2048 peliin perustuva replika, jossa voi itse valita pelilaudan koon. 

### Suunnitellut toiminnallisuudet
- Peliä on mahdollista pelata haluamallaan pelikoolla. 
- Sovellus pitää kirjaa pelaajien high scoresta ja tallentaa ne Sqlite-tietokantaan pelimuodoilla 3x3, 4x4 ja 5x5. Muissa pelilautojen koissa sovellus laskee pelaajan pisteet, mutta niitä ei lasketa high score-järjestelmään. 
- Sovelluksessa on osio, josta näkee TOP 5 pisteet pelien 3x3, 4x4 ja 5x5 koille. Pelin päätyttyä pelaajan pisteet tallennetaan sqlite-tietokantaan jos pisteet riittävät top5 sijotuksiin. 
- Peliin luodaan AI Doge ominaisuus joka pelaa itsestään peliä. 

### Ylläpitäjän toiminnallisuus
Ylläpitäjä huolehtii että tietokanta toimii sovelluksen kanssa, jotta high scoren hakeminen ja tallentaminen tietokantaa toimii moitteettomasti. Mikäli tietokanta ei jostain syystä toimisi, sovellus täytyy toimia pelattavana, mutta luonnollisesti ilman mahdollisuutta lisätä high scorea tietokantaan. 

### Toimintaympäristön rajoitteet
Sovellus toimii macOS ja Linux pohjaisilla käyttöjärjestelmillä.

### Käyttöliittymä
Sovelluksen käyttöliittymä tehdään Javan javafx-kirjastolla. Käyttöliittymässä on aloitusnäkymä, josta voi valita haluamansa pelin koon, AI Doge-pelimuodon valinnan tai high score näkymään siirtymisen. Pelinäkymän käynnistäessä aukeaa näkymä, jossa voi nuolinäppäimillä tehdä siirtoja ja pelin päätyttyä (ei siirtoja jäljellä) näkymän päälle lisätään tila (stackpane), jossa näytetään kertyneet pisteet, uuden pelin aloittamiseen mahdollistava nappi ja high scorejen tarkasteluun siirtyminen oleva napi. Mikäli pelattu tulos on vähintään top5 pisteiden verran, tulos tallennetaan tietokantaan automaattisesti.

### Jatkokehitysideoita
* Sääntösivusto, josta käytäisiin pelin säännöt lävitse esimerkiksi miten palaset yhdistyvät toisiinsa ja missä tilanteissa eivät.
* Tietokannan siirto internettiin, johon voisi omalla nimimerkillä tallentaa tuloksensa koneeltaan.
* Doge AI:n kehitys, jotta doge osaisi pelata pelin kokonaan lävitse. (Minimax:n algoritmin impletointi)
* Doge AI:n muiden lautojen koon pelaamisen mahdollisuus.
