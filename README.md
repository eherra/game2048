## Game 2048

A game where you move squares on board and try to sum same valued squares with each other. The game is lost when there're no moves left (squares are not summing with each other (up, down, left or right)), and won when one of the square's value is 2048.

The game will start with two random squares values of '2', and if summing has happened after a move (up, down, left or right), the board will be updated with random coordinated square with value of '2' or '4' (10% chance for value '4')

* [Instructions](https://github.com/eherra/ot-harjoitustyo/blob/main/dokumentaatio/kayttoohje.md)

<img src="https://github.com/eherra/game2048/blob/main/dokumentaatio/kuvat/mainmenu.png">

### Dokumentation
* [Architecture](https://github.com/eherra/ot-harjoitustyo/blob/main/dokumentaatio/arkkitehtuuri.md)
* [Requirements specification](https://github.com/eherra/ot-harjoitustyo/blob/main/dokumentaatio/vaatimusmaarittely.md)
* [Tests](https://github.com/eherra/ot-harjoitustyo/blob/main/dokumentaatio/testausdokumentti.md)

## Terminal commands:
<sub><b>These commands can be executed from the root folder (_game2048_) on terminal.</b></sub>

To start the game:
```console
mvn compile exec:java -Dexec.mainClass=game2048.ui.Ui
```

### Testing the program
To run JUnit tests:
```console
mvn test
```

To get Jacoco report:
```console
mvn test jacoco:report
```

### Checkstyle
To run checkstyle validation:
```console
mvn jxr:jxr checkstyle:checkstyle
```

### Generating executable file
To create jar file:
```console
mvn package
```

### Javadoc
To generate JavaDoc:
```console
mvn javadoc:javadoc
```
