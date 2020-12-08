## Game 2048

A game where you move squares on board and try to sum same valued squares with each other. The game is lost when there're no moves left (squares are not summing with each other (up, down, left or right)), and won when one of the square's value is 2048.

The game will start with two random squares values of '2', and if summing has happened after a move (up, down, left or right), the board will be updated with random coordinated square with value of '2' or '4' (10% chance for value '4')

### Dokumentation
* [Arkkitehtuuri](https://github.com/eherra/ot-harjoitustyo/blob/main/dokumentaatio/arkkitehtuuri.md)
* [Määrittelydokumentti](https://github.com/eherra/ot-harjoitustyo/blob/main/dokumentaatio/maarittelydokumentti.md)
* [Tuntikirjanpito](https://github.com/eherra/ot-harjoitustyo/blob/main/dokumentaatio/tuntikirjanpito.md)

### Releases'
* [v1.5](https://github.com/eherra/ot-harjoitustyo/releases/tag/viikko6)
* [v1.2](https://github.com/eherra/ot-harjoitustyo/releases/tag/viikko5v1)

### Terminal commands:
To start the game:
```console
mvn compile exec:java -Dexec.mainClass=game2048.ui.Ui
```

To run tests:
```console
mvn test
```

To get Jacoco report:
```console
mvn test jacoco:report
```

To run checkstyle:
```console
mvn jxr:jxr checkstyle:checkstyle
```

To create jar file:
```console
mvn package
```

To generate JavaDoc:
```console
mvn javadoc:javadoc
```
