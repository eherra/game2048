## Määrittelydokumentti
### Sovelluksen tarkoitus
Sovellus on 2048 peliin perustuva replika, jossa voi itse valita pelilaudan koon. 

### Suunnitellut toiminnallisuudet
- Peliä on mahdollista pelata haluamallaan pelikoolla. (tehty)
- Sovellus pitää kirjaa pelaajien high scoresta ja tallentaa ne Sqlite tietokantaan pelimuodoilla 3x3, 4x4 ja 5x5. Muissa pelilautojen koissa sovellus laskee pelaajan pisteet, mutta niitä ei lasketa high score-järjestelmään. (tehty)
- Sovelluksessa on osio, josta näkee TOP 5 pisteet koille 3x3, 4x4 ja 5x5. Pelin päätyttyä pelaajan pisteet tallennetaan sqlite-tietokantaan jos pisteet riittävät top5 sijotuksiin. (tehty)
- Peliin luodaan AI Doge ominaisuus joka pelaa itsestään peliä. (tehty)

### Ylläpitäjän toiminnallisuus
Ylläpitäjä huolehtii että tietokanta toimii sovelluksen kanssa, jotta high scoren hakeminen ja tallentaminen tietokantaa toimii moitteettomasti. Mikäli tietokanta ei jostain syystä toimisi, sovellus täytyy toimia pelattavana, mutta luonnollisesti ilman mahdollisuutta lisätä high scorea tietokantaan. 

### Toimintaympäristön rajoitteet
Sovellus toimii macOS ja Linux pohjaisilla käyttöjärjestelmillä.

### Käyttöliittymä
Sovelluksen käyttöliittymä tehdään Javan javafx-kirjastolla. Käyttöliittymä on yksinkertainen, jossa sovelluksen käynnistäessä aukeaa pelinäkymä ja pelin päätyttyä näkymän päälle luodaan näkymä jossa mahdollisuus uuden pelin aloittamiseen ja oman scoren tallentamiseen tietokantaan. (tehty)
