# Testausdokumentti

Ohjelmaa on testattu laajasti automaattisia JUnit-yksikkötestejä apuna käyttäen sekä manuaalisesti graafisen liittymän kautta, sekä täten samalla peliloogiikka on ollut testauksen alle.

## Yksikkö- ja integraatiotestaus

### Pelilogiikka

Ohjelman moottorina toimivat GameLogic-, ScoreBoard- ja MoveExecutor-luokat (game2048.domain) ovat testattu laajasti sekä yksikkötestein että manuaalisesti, jotta ne noudattavat tarkoin pelin sääntöjä
(oikeat yhdistykset paloille, ei liika yhdistymiä, random-palasen lisäys, pistelaskun oikeallisuus).


