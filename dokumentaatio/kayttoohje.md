## Käyttöohje
Kloonaa tiedosto terminaalistasi komennolla:
```console
git clone https://github.com/eherra/ot-harjoitustyo.git
```
* Ja käynnistä terminaalista komennolla: 
```console
mvn compile exec:java -Dexec.mainClass=game2048.ui.Ui
```

Tai lataa jar-tiedosto alla olevan linkin takaa:

[lataa jar](https://github.com/eherra/ot-harjoitustyo/releases/tag/loppupalautus)

* ja käynnistä jar-tiedosto terminaalista komennolla:
```console
java -jar game2048.jar
```
### Main menu
<img src="https://github.com/eherra/ot-harjoitustyo/blob/main/dokumentaatio/kuvat/mainmenu.png"> 

### Pelin aloittaminen
Voit joko painaa "Quick start" nappia aloittaaksesi klassisen 4x4 ruudukon pelin.
Vaihtoehtoisesti voit kirjoittaa kohtaan "Board Set up" haluamasi laudan koon väliltä 3-9. Tämän jälkeen napin "Play!" tai näppäimistön "Enter":in painaminen käynnistää pelin. 
<img src="https://i.ibb.co/68kCg0V/Screen-Shot-2020-12-05-at-18-15-03.png"> 

### Pelin säännöt 
Pelissä yhdistellään saman arvoisia lukuja toisiinsa siihen asti kun yksikään luku ei enää yhdisty toisiinsa pelilaudan ollessa täynnä ruutuja. Alle havainnollistava kuvasarja:

<img src="https://i.ibb.co/D7Vjrv1/Screen-Shot-2020-12-05-at-18-15-31.png"> 

Siirto vasemmalle:

<img src="https://i.ibb.co/7QqmhzC/Screen-Shot-2020-12-05-at-18-15-40.png"> 

### High score
Pelin tulokset tallennetaan Sqlite-tietokantaan. Highscore-napin takaa löydät TOP5 pisteet lautojen 3x3, 4x4 ja 5x5 peleistä.

<img src="https://i.ibb.co/bsZM2gW/Screen-Shot-2020-12-05-at-18-16-20.png" width="501" heigth="315"> 

### AI Doge
Kyllästynyt pelaamaan itse? Anna AI Dogen pelailla puolestasi. 
"Release doge" nappi käynnistää 4x4 kokoisen pelin, jota doge pelailee puolestasi ja voit itse keskittyä vaikka kutomaan sukkia. 

<img src="https://i.ibb.co/g9VcG5N/Screen-Shot-2020-12-05-at-18-17-51.png">

<p float="left">
  <img src="https://i.ibb.co/SBHRVzn/Screen-Shot-2020-12-09-at-18-24-28.png width="301" heigth="387">
  <img src="https://i.ibb.co/sFWst0Z/Screen-Shot-2020-12-09-at-18-25-01.png width="301" heigth="400">
</p>

