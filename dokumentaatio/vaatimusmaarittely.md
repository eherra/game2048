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
Sovelluksen käyttöliittymä tehdään Javan javafx-kirjastolla. Käyttöliittymä on yksinkertainen, jossa sovelluksen käynnistäessä aukeaa pelinäkymä ja pelin päätyttyä näkymän päälle luodaan näkymä (stackpane) jossa mahdollisuus uuden pelin aloittamiseen ja high scorejen tarkasteluun. Mikäli pelattu tulos on vähintään top5 pisteiden verran, tulos tallennetaan tietokantaan.
